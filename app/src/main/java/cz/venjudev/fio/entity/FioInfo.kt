package cz.venjudev.fio.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*

@Entity(tableName = "info")
data class FioInfo(
    @PrimaryKey @ColumnInfo(name = "accountId") @SerializedName("accountId") val accountId: String,
    @ColumnInfo(name = "bankId") @SerializedName("bankId") val bankId: String?,
    @ColumnInfo(name = "currency") @SerializedName("currency") val currency: String?,
    @ColumnInfo(name = "iban") @SerializedName("iban") val iban: String?,
    @ColumnInfo(name = "bic") @SerializedName("bic") val bic: String?,
    @ColumnInfo(name = "openingBalance") @SerializedName("openingBalance") val openingBalance: BigDecimal?,
    @ColumnInfo(name = "closingBalance") @SerializedName("closingBalance") val closingBalance: BigDecimal?,
    @ColumnInfo(name = "dateStart") @SerializedName("dateStart") val dateStart: Date?,
    @ColumnInfo(name = "dateEnd") @SerializedName("dateEnd") val dateEnd: Date?,
    @ColumnInfo(name = "yearList") @SerializedName("yearList") val yearList: String?,
    @ColumnInfo(name = "idList") @SerializedName("idList") val idList: Long?,
    @ColumnInfo(name = "idFrom") @SerializedName("idFrom") val idFrom: Long?,
    @ColumnInfo(name = "idTo") @SerializedName("idTo") val idTo: Long?,
    @ColumnInfo(name = "idLastDownload") @SerializedName("idLastDownload") val idLastDownload: Int?
)