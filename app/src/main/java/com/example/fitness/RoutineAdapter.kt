package com.example.fitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitness.model.Routine

class RoutineAdapter(
    private val onRoutineClick: (Routine) -> Unit
) : RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>() {

    private var routines: List<Routine> = emptyList()

    fun submitList(newRoutines: List<Routine>) {
        routines = newRoutines
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_routine, parent, false)
        return RoutineViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.bind(routines[position])
    }

    override fun getItemCount(): Int = routines.size

    inner class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRoutineName: TextView = itemView.findViewById(R.id.tvRoutineName)
        private val tvExerciseCount: TextView = itemView.findViewById(R.id.tvExerciseCount)

        fun bind(routine: Routine) {
            tvRoutineName.text = routine.name
            tvExerciseCount.text = "${routine.exercises.size}개 운동"

            itemView.setOnClickListener {
                onRoutineClick(routine)
            }
        }
    }
}
