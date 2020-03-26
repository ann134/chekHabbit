package com.isableto.chekhabbit

import android.app.Application
import com.isableto.chekhabbit.database.HabitDatabase
import net.danlew.android.joda.JodaTimeAndroid

class App : Application() {

    companion object {
        lateinit var database: HabitDatabase
    }

    override fun onCreate() {
        super.onCreate()
        //todo сделать покрасивее
        database = let {
            HabitDatabase.getInstance(this)!!
        }
        JodaTimeAndroid.init(this);
    }
}