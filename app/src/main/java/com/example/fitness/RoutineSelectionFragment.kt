package com.example.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitness.data.RepositoryProvider
import com.example.fitness.model.Routine
import kotlinx.coroutines.launch

class RoutineSelectionFragment : Fragment() {

    private val routineRepo by lazy {
        RepositoryProvider.getRoutineRepository()
    }

    private lateinit var rvRoutines: RecyclerView
    private lateinit var layoutEmptyState: LinearLayout
    private lateinit var adapter: RoutineAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_routine_selection, container, false)

        initViews(view)
        loadRoutines()

        return view
    }

    private fun initViews(view: View) {
        rvRoutines = view.findViewById(R.id.rvRoutines)
        layoutEmptyState = view.findViewById(R.id.layoutEmptyState)

        // RecyclerView 설정
        adapter = RoutineAdapter { routine ->
            onRoutineSelected(routine)
        }
        rvRoutines.layoutManager = LinearLayoutManager(requireContext())
        rvRoutines.adapter = adapter
    }

    private fun loadRoutines() {
        lifecycleScope.launch {
            val routines = routineRepo.getAllRoutines()

            if (routines.isEmpty()) {
                rvRoutines.visibility = View.GONE
                layoutEmptyState.visibility = View.VISIBLE
            } else {
                rvRoutines.visibility = View.VISIBLE
                layoutEmptyState.visibility = View.GONE
                adapter.submitList(routines)
            }
        }
    }

    private fun onRoutineSelected(routine: Routine) {
        // 운동 실행 화면으로 이동
        val bundle = Bundle().apply {
            putString("routineId", routine.id)
            putString("routineName", routine.name)
        }
        findNavController().navigate(R.id.action_routine_selection_to_workout_execution, bundle)
    }
}
