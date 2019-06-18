package cz.venjudev.fio.entity

import com.google.gson.annotations.SerializedName

data class FioStringColumn(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)