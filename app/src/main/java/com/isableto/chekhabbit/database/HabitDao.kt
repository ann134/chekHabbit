package com.isableto.chekhabbit.database

import androidx.room.*
import com.isableto.chekhabbit.database.model.Habit
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface HabitDao {

    @Query("SELECT * from habits")
    fun getAll(): Flowable<List<Habit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Habit): Completable

    @Update
    fun update(user: Habit): Completable

    @Query("DELETE from habits")
    fun deleteAll()

}