package cz.venjudev.fio.persistance.db

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

class Converters {

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toLong(value: Date?): Long? {
        return value?.time
    }

    @TypeConverter
    fun toBigDecimal(value: Double?): BigDecimal? {
        return if (value == null) null else BigDecimal.valueOf(value)
    }

    @TypeConverter
    fun toDouble(value: BigDecimal?): Double? {
        return value?.toDouble()
    }
}