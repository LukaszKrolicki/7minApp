package eu.pl.snk.senseibunny.a7minuteapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "activity-table")
data class ActivityEntity (
    @PrimaryKey(autoGenerate = true) //it makes sure every entry is unique
    var id: Int=0,

    var date: String,

    )