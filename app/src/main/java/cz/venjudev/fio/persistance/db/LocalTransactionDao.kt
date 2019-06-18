package cz.venjudev.fio.persistance.db

import androidx.room.*
import cz.venjudev.fio.entity.LocalFioTransaction


@Dao
interface LocalTransactionDao {
    @Query("SELECT * FROM transactions order by date desc")
    fun all(): List<LocalFioTransaction>

    @Query("SELECT * FROM transactions WHERE id IN (:id)")
    fun findById(id: String): LocalFioTransaction

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(transactions: List<LocalFioTransaction>)

    @Delete
    fun delete(transaction: LocalFioTransaction)
}