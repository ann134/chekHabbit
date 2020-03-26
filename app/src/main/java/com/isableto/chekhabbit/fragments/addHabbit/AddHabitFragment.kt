package com.isableto.chekhabbit.fragments.addHabbit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.isableto.chekhabbit.R
import com.isableto.chekhabbit.database.model.Habit
import com.isableto.chekhabbit.fragments.HabitViewModel
import kotlinx.android.synthetic.main.fragment_add_habbit.*
import java.util.*

class AddHabitFragment : Fragment(R.layout.fragment_add_habbit), View.OnClickListener {

    private lateinit var viewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(HabitViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_add_habit.setOnClickListener(this)
        bt_back.setOnClickListener(this)
        bt_clear.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_add_habit -> {
                addHabit()
            }
            R.id.bt_back -> {
                Navigation.findNavController(v).popBackStack()
            }
            R.id.bt_clear -> {
                et_habit_name.setText("")
            }
        }
    }

    private fun addHabit() {
        if (et_habit_name.text.toString().isBlank()){
            Toast.makeText(context, "Веведите имя привычки", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.insertHabit(Habit(et_habit_name.text.toString(), Date()))
            .subscribe {
                Navigation.findNavController(this.requireView()).popBackStack()
            }
    }
}