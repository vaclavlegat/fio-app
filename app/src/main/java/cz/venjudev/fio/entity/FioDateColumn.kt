package cz.venjudev.fio.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class FioDateColumn(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Date
)