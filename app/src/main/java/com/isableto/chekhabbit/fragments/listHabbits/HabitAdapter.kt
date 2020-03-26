package com.isableto.chekhabbit.fragments.listHabbits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isableto.chekhabbit.R
import com.isableto.chekhabbit.database.model.Habit
import kotlinx.android.synthetic.main.item_habbit.view.*

class HabitAdapter(private val clickListener: (Habit?) -> Unit) :
    RecyclerView.Adapter<HabitAdapter.HabitHolder>() {

    var list : ArrayList<Habit?> = ArrayList()
        set(value) {
            value.add(null)
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_habbit, parent, false)
        return HabitHolder(v)
    }

    override fun onBindViewHolder(holder: HabitHolder, position: Int) {
        val item = list[position]

        if (item == null){
            holder.bindEmpty(clickListener)
        } else {
            holder.bindItem(item, clickListener)
        }

        /*item?.let{
            holder.bindItem(it, clickListener)
        } ?: {
            holder.bindEmpty(clickListener)
        }*/

        /*when (position) {
            itemCount - 1 -> holder.bindEmpty(clickListener)
            else -> holder.bindItem(list[position], clickListener)
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class HabitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: Habit, clickListener: (Habit) -> Unit) {
            itemView.tv_name.visibility = View.VISIBLE
            itemView.tv_days_value.visibility = View.VISIBLE
            itemView.tv_missed_days.visibility = View.VISIBLE
            itemView.tv_day.visibility = View.VISIBLE
            itemView.bg_view.setImageResource(R.drawable.ic_android_tr)

            itemView.tv_name.text = item.name
            itemView.tv_days_value.text = item.checkedDaysCount.toString()
            itemView.tv_missed_days.text = String.format(itemView.context.getString(R.string.missed), item.missedDaysCount)

            when (item.checkedDaysCount) {
                in 10..20 -> itemView.tv_day.text = "дней"
                else -> itemView.tv_day.text = getDay(item.checkedDaysCount)
            }
            when (item.canCheckToday) {
                true -> {
                    itemView.bt_check_gray.visibility = View.INVISIBLE
                    itemView.bt_check_red.visibility = View.VISIBLE
                }
                false -> {
                    itemView.bt_check_gray.visibility = View.VISIBLE
                    itemView.bt_check_red.visibility = View.INVISIBLE
                }
            }
            itemView.bt_check_red.setOnClickListener { clickListener(item) }
        }

        fun bindEmpty(clickListener: (Habit?) -> Unit) {
            itemView.tv_name.visibility = View.INVISIBLE
            itemView.tv_days_value.visibility = View.INVISIBLE
            itemView.tv_missed_days.visibility = View.INVISIBLE
            itemView.tv_day.visibility = View.INVISIBLE

            itemView.bt_check_gray.visibility = View.INVISIBLE
            itemView.bt_check_red.visibility = View.INVISIBLE

            itemView.bg_view.setImageResource(R.drawable.ic_add_black)
            itemView.bg_view.setOnClickListener { clickListener(null) }
        }

        private fun getDay(int: Int): String {
            return when (int) {
                0 -> "Дней"
                1 -> "День"
                2 -> "Дня"
                3 -> "Дня"
                4 -> "Дня"
                5 -> "Дней"
                6 -> "Дней"
                7 -> "Дней"
                8 -> "Дней"
                9 -> "Дней"
                else -> "Дней"
            }
        }
    }
}