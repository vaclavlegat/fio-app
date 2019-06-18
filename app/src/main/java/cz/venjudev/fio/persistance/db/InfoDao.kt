package cz.venjudev.fio.persistance.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.venjudev.fio.entity.FioInfo

@Dao
interface InfoDao {
    @Query("SELECT * FROM info limit 1")
    fun getInfo(): FioInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo(info: FioInfo)

}