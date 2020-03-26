package com.isableto.chekhabbit.fragments

import com.isableto.chekhabbit.App
import com.isableto.chekhabbit.database.HabitMapper
import com.isableto.chekhabbit.database.model.Habit
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HabitRepository {

    fun getHabits(): Flowable<List<Habit>> {
        return App.database.habitDao().getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .map(HabitMapper::mapHabit)
    }

    fun insertHabit(habit: Habit): Completable = App.database.habitDao()
            .insert(habit)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())


    fun updateHabit(habit: Habit): Completable {
        return App.database.habitDao()
            .update(habit)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteHabits() {
        return App.database.habitDao()
            .deleteAll()
    }

}