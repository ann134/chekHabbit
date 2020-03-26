package com.isableto.chekhabbit.database

import com.isableto.chekhabbit.database.model.Habit
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object HabitMapper {

    fun mapHabit(habits: List<Habit>): List<Habit> {
        habits.forEach { habit ->
            habit.canCheckToday()
            habit.setMissedDays()
        }
        return habits
    }

    private fun Habit.setMissedDays(){
        val different: Long = Date().getTime() - createdAt.getTime()
        val daysInMilli = 1000 * 60 * 60 * 24
        var dif = (different / daysInMilli).toInt() - checkedDaysCount

        //todo
        if (canCheckToday){
            dif--
        }

        missedDaysCount = when (dif) {
            in 0..Int.MAX_VALUE -> dif
            else -> 0
        }
    }

    private fun Habit.canCheckToday(){
        if (lastCheckDate == null){
            canCheckToday = true
        }
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

        //todo
        try {
            canCheckToday = formatter.format(Date()) != formatter.format(lastCheckDate)
        } catch (e:Exception){

        }
    }
}