package cz.venjudev.fio.repository

import android.util.Log
import cz.venjudev.fio.api.FioApi
import cz.venjudev.fio.entity.LocalFioTransaction
import cz.venjudev.fio.entity.MainActivityModel
import cz.venjudev.fio.persistance.PreferencesManager
import cz.venjudev.fio.persistance.db.InfoDao
import cz.venjudev.fio.persistance.db.LocalTransactionDao
import cz.venjudev.fio.ui.UICallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class TransactionRepository(
    private val transactionDao: LocalTransactionDao,
    private val infoDao: InfoDao,
    private val preferencesManager: PreferencesManager
) {

    companion object {
        const val TAG = "TransactionRepository"
        const val DATE_PATTERN = "yyyy-MM-dd"
        const val DATE_FROM = "2018-04-01"
        const val FIO_TOKEN = "insert fio api token here"

    }

    fun getTransactions(uiCallback: UICallback<MainActivityModel>) {
        val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        val nowDate = formatter.format(Date())
        val service = FioApi.create()
        uiCallback.onProgress()

        CoroutineScope(Dispatchers.IO).launch {
            val now = Date()
            val lastCall = preferencesManager.getLastApiCall() ?: now

            val beforeThirtySeconds = Date(now.time - 1000 * 30)

            // Fio BE can be called only every 30 s
            if (beforeThirtySeconds.after(lastCall) || lastCall == now) {
                val request = service.getTransactions(
                    FIO_TOKEN,
                    DATE_FROM,
                    nowDate
                )

                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        preferencesManager.setLastApiCall(now)
                        infoDao.insertInfo(response.body()?.accountStatement!!.info)
                        transactionDao.insertAll(response.body()?.accountStatement?.transactionList!!.mapToLocalFioTransactions())
                    }

                } catch (e: Throwable) {
                    Log.e(TAG, "Error loading transactions.", e)
                }
            } else {
                Log.d(TAG, "Do not call Fio BE yet.")
            }

            val data = transactionDao.all()
            val info = infoDao.getInfo()

            withContext(Dispatchers.Main) {
                uiCallback.onSuccess(MainActivityModel(info, data))
            }
        }
    }

    fun getTransaction(id: String, uiCallback: UICallback<LocalFioTransaction>) {

        uiCallback.onProgress()
        CoroutineScope(Dispatchers.IO).launch {

            val transaction = transactionDao.findById(id)

            withContext(Dispatchers.Main) {
                uiCallback.onSuccess(transaction)
            }
        }
    }

}

