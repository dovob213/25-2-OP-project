package com.example.fitness

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitness.model.ExerciseSet
import com.google.android.material.textfield.TextInputEditText

class ExerciseSetAdapter : RecyclerView.Adapter<ExerciseSetAdapter.SetViewHolder>() {

    private data class SetData(
        var weight: Double = 0.0,
        var reps: Int = 0
    )

    private val sets = mutableListOf<SetData>()

    fun addSet(weight: Double, reps: Int) {
        sets.add(SetData(weight, reps))
        notifyItemInserted(sets.size - 1)
    }

    fun clearSets() {
        sets.clear()
        notifyDataSetChanged()
    }

    fun getSets(): List<ExerciseSet> {
        return sets.filter { it.weight > 0 && it.reps > 0 }
            .map { ExerciseSet(it.weight, it.reps, true) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_set, parent, false)
        return SetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.bind(position + 1, sets[position])
    }

    override fun getItemCount(): Int = sets.size

    inner class SetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSetNumber: TextView = itemView.findViewById(R.id.tvSetNumber)
        private val etWeight: TextInputEditText = itemView.findViewById(R.id.etWeight)
        private val etReps: TextInputEditText = itemView.findViewById(R.id.etReps)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(setNumber: Int, setData: SetData) {
            tvSetNumber.text = setNumber.toString()

            // 기존 리스너 제거
            etWeight.removeTextChangedListener(weightWatcher)
            etReps.removeTextChangedListener(repsWatcher)

            // 데이터 표시
            if (setData.weight > 0) {
                etWeight.setText(setData.weight.toString())
            }
            if (setData.reps > 0) {
                etReps.setText(setData.reps.toString())
            }

            // 무게 입력 리스너
            etWeight.addTextChangedListener(weightWatcher)

            // 횟수 입력 리스너
            etReps.addTextChangedListener(repsWatcher)

            // 삭제 버튼
            btnDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    sets.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, sets.size)
                }
            }
        }

        private val weightWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    sets[position].weight = s.toString().toDoubleOrNull() ?: 0.0
                }
            }
        }

        private val repsWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    sets[position].reps = s.toString().toIntOrNull() ?: 0
                }
            }
        }
    }
}
