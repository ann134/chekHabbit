package com.isableto.chekhabbit.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey val name: String,
    val createdAt: Date,
    var checkedDaysCount: Int = 0,
    var lastCheckDate: Date? = null,
    var missedDaysCount: Int = 0,
    var canCheckToday: Boolean = true
)