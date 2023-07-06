package eu.pl.snk.senseibunny.a7minuteapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AcitivityDao {
    @Insert
    suspend fun insert(activityEntity: ActivityEntity)

    @Query("SELECT * FROM `activity-table`")
    fun fetchAllActivity(): Flow<List<ActivityEntity>>
}