package cz.venjudev.fio.ui.transactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.venjudev.fio.entity.MainActivityModel
import cz.venjudev.fio.repository.TransactionRepository
import cz.venjudev.fio.ui.UICallback

class TransactionsViewModel(val repo: TransactionRepository) : ViewModel() {

    val transactionsLiveData = MutableLiveData<MainActivityModel>()
    val progress = MutableLiveData<Boolean>()

    fun getTransactions(): MutableLiveData<MainActivityModel> {
        repo.getTransactions(object : UICallback<MainActivityModel> {
            override fun onProgress() {
                progress.postValue(true)
            }

            override fun onSuccess(data: MainActivityModel) {
                progress.postValue(false)
                transactionsLiveData.postValue(data)
            }

            override fun onError() {
                progress.postValue(false)
            }

        })

        return transactionsLiveData
    }

}