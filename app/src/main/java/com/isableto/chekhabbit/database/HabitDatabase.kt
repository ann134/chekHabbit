package com.isableto.chekhabbit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.isableto.chekhabbit.database.model.Habit

@Database(entities = [Habit::class], version = 1)
@TypeConverters(Converter::class)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {

        private var INSTANCE: HabitDatabase? = null

        fun getInstance(context: Context): HabitDatabase? {
            if (INSTANCE == null) {
                synchronized(HabitDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            HabitDatabase::class.java, "habit.db"
                        )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}