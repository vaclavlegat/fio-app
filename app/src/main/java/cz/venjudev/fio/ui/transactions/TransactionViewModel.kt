package cz.venjudev.fio.ui.transactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.venjudev.fio.entity.LocalFioTransaction
import cz.venjudev.fio.repository.TransactionRepository
import cz.venjudev.fio.ui.UICallback

class TransactionViewModel(val repo: TransactionRepository) : ViewModel() {

    val transactionLiveData = MutableLiveData<LocalFioTransaction>()
    val progress = MutableLiveData<Boolean>()

    fun getTransaction(id: String): MutableLiveData<LocalFioTransaction> {
        repo.getTransaction(id, object : UICallback<LocalFioTransaction> {
            override fun onProgress() {
                progress.postValue(true)
            }

            override fun onSuccess(data: LocalFioTransaction) {
                progress.postValue(false)
                transactionLiveData.postValue(data)
            }

            override fun onError() {
                progress.postValue(false)
            }

        })

        return transactionLiveData
    }

}