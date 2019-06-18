package cz.venjudev.fio.entity

import com.google.gson.annotations.SerializedName


data class FioStatementResponse(@SerializedName("accountStatement") val accountStatement: FioAccountStatement)