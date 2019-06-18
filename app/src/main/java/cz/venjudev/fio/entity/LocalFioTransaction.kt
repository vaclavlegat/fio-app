package cz.venjudev.fio.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*


@Entity(tableName = "transactions")
data class LocalFioTransaction(
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "amount") val amount: BigDecimal,
    @ColumnInfo(name = "account") val account: String?,
    @ColumnInfo(name = "bankCode") val bankCode: String?,
    @ColumnInfo(name = "ks") val ks: String?,
    @ColumnInfo(name = "vs") val vs: String?,
    @ColumnInfo(name = "ss") val ss: String?,
    @ColumnInfo(name = "userId") val userId: String?,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "accountName") val accountName: String?,
    @ColumnInfo(name = "bankName") val bankName: String?,
    @ColumnInfo(name = "currency") val currency: String?,
    @ColumnInfo(name = "messageForRecipient") val messageForRecipient: String?,
    @ColumnInfo(name = "idP") val idP: String?,
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "commnent") val comment: String?
) {

    fun getTitle(): String? {

        if (userId != null) {
            return if (userId.contains(":")) {
                val data = userId.split(",")
                data[0].substring(data[0].indexOf(":") + 1).trim()
            } else {
                userId
            }
        }

        if (messageForRecipient != null) {
            return messageForRecipient
        }

        return type

    }

    fun getAccountInfo(): String? {
        var accountInfo: String? = ""

        accountName?.let {
            accountInfo = "$it - "
        }

        account?.let {
            accountInfo += it
        }

        bankCode?.let {
            accountInfo += "/$it"
        }

        return if (account != null) accountInfo else null
    }
}

