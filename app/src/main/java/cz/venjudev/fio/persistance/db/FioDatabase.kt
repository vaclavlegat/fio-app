package cz.venjudev.fio.persistance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.venjudev.fio.entity.FioInfo
import cz.venjudev.fio.entity.LocalFioTransaction


@Database(entities = [LocalFioTransaction::class, FioInfo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FioDatabase : RoomDatabase() {
    abstract fun localTransactionDao(): LocalTransactionDao
    abstract fun infoDao(): InfoDao
}