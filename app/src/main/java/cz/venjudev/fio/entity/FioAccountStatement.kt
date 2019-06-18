package cz.venjudev.fio.entity

import com.google.gson.annotations.SerializedName

data class FioAccountStatement(
    @SerializedName("info") val info: FioInfo,
    @SerializedName("transactionList") val transactionList: FioTransactionList
)