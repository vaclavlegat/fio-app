package cz.venjudev.fio.entity

import com.google.gson.annotations.SerializedName


data class FioTransactionList(@SerializedName("transaction") val transactions: List<FioTransaction>) {

    fun mapToLocalFioTransactions(): List<LocalFioTransaction> {
        val localTransactions = transactions.map {
            LocalFioTransaction(
                it.date.value,
                it.amount.value,
                it.account?.value,
                it.bankCode?.value,
                it.ks?.value,
                it.vs?.value,
                it.ss?.value,
                it.userId?.value,
                it.author?.value,
                it.type.value,
                it.accountName?.value,
                it.bankName?.value,
                it.currency.value,
                it.messageForRecipient?.value,
                it.idP.value,
                it.id.value,
                it.comment.value
            )
        }
        return localTransactions
    }

}