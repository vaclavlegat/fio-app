package cz.venjudev.fio.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FioTransaction(@SerializedName("column0") val date: FioDateColumn, // Datum
                          @SerializedName("column1") val amount: FioBigDecimalColumn, // Objem
                          @SerializedName("column2") val account: FioStringColumn?, // Protiúčet
                          @SerializedName("column3") val bankCode: FioStringColumn?, // Kód banky
                          @SerializedName("column4") val ks: FioStringColumn?, // KS
                          @SerializedName("column5") val vs: FioStringColumn?, // VS
                          @SerializedName("column6") val ss: FioStringColumn?, // SS
                          @SerializedName("column7") val userId: FioStringColumn?, // Uživatelská identifikace
                          @SerializedName("column8") val type: FioStringColumn, // Typ
                          @SerializedName("column9") val author: FioStringColumn?, // Provedl
                          @SerializedName("column10") val accountName: FioStringColumn?, // Název protiúčtu
                          @SerializedName("column12") val bankName: FioStringColumn?, // Název banky
                          @SerializedName("column14") val currency: FioStringColumn, // Měna
                          @SerializedName("column16") val messageForRecipient: FioStringColumn, // Zpráva pro příjemce
                          @SerializedName("column17") val idP: FioStringColumn, // ID pokynu
                          @SerializedName("column22") val id: FioStringColumn, // ID pohybu
                          @SerializedName("column25") val comment: FioStringColumn // Komentář
) : Serializable