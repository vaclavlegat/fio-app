package cz.venjudev.fio.entity

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class FioBigDecimalColumn(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: BigDecimal
)