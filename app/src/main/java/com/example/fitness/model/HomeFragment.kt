package model

class HomeFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // C에게서 받은 루틴 ID
        val routineId = arguments?.getString("routineId")
        if (routineId != null) {
            lifecycleScope.launch {
                val routine = routineRepo.getRoutine(routineId)
                if (routine != null) {
                    sessionManager.startRoutine(routine)
                }
            }
        }
    }
}