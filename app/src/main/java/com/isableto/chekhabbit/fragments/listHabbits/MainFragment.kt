package com.isableto.chekhabbit.fragments.listHabbits

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.isableto.chekhabbit.R
import com.isableto.chekhabbit.database.model.Habit
import com.isableto.chekhabbit.fragments.HabitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment(R.layout.fragment_main), View.OnClickListener {

    private lateinit var viewModel: HabitViewModel
    private var habitDisposable = CompositeDisposable()
    lateinit var  adapter : HabitAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(HabitViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_add_habit.setOnClickListener(this)

        adapter = HabitAdapter{ partItem: Habit? ->
                onHabitChecked(partItem)
            }
        recycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun onStop() {
        super.onStop()
        habitDisposable.clear()
    }

    private fun loadData() {
        habitDisposable.add(
            viewModel.getHabits()
                .subscribe {
                    showHabitsList(it)
                }
        )
    }

    private fun showHabitsList(list: List<Habit>) {
        if (list.isEmpty()) {
            showEmpty()
            return
        }
        recycler.visibility = View.VISIBLE
        adapter.list = ArrayList(list)
    }

    private fun showEmpty() {
        tv_add_habit.visibility = View.VISIBLE
        bt_add_habit.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        loadFragment()
    }

    private fun onHabitChecked(habit: Habit?) {

        if (habit == null){
            loadFragment()
            return
        }


        habit.checkedDaysCount++
        habit.lastCheckDate = Date()
        habitDisposable.add(
            viewModel.updateHabit(habit)
                .subscribe{
            }
        )


       /* habit?.let {
            it.checkedDaysCount++
            it.lastCheckDate = Date()
            habitDisposable.add(
                viewModel.updateHabit(it).subscribe()
            )
        } ?: loadFragment()*/
    }

    private fun loadFragment() {
        Navigation.findNavController(bt_add_habit)
            .navigate(R.id.action_mainFragment_to_addHabbitFragment)
    }
}