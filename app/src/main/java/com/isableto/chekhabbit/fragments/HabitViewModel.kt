package com.isableto.chekhabbit.fragments

import androidx.lifecycle.ViewModel
import com.isableto.chekhabbit.database.model.Habit
import io.reactivex.Completable
import io.reactivex.Flowable

class HabitViewModel : ViewModel() {

    private var repository: HabitRepository = HabitRepository()

    fun getHabits(): Flowable<List<Habit>> {
        return repository.getHabits()
    }

    fun insertHabit(habit: Habit): Completable {
        return repository.insertHabit(habit)
    }

    fun updateHabit(habit: Habit): Completable {
        return repository.updateHabit(habit)
    }
}