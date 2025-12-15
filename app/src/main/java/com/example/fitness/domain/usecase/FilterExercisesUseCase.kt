package com.example.fitness.domain.usecase

import com.example.fitness.model.Exercise
import com.example.fitness.model.ExerciseCategory
import com.example.fitness.model.DifficultyLevel
import com.example.fitness.data.filter.*
import com.example.fitness.data.repository.ExerciseRepository

/**
 * 운동 필터링 Use Case
 * - 복합 필터링 로직 처리
 */
class FilterExercisesUseCase(
    private val repository: ExerciseRepository
) {
    operator fun invoke(
        category: ExerciseCategory? = null,
        difficulty: DifficultyLevel? = null,
        targetMuscle: String? = null
    ): List<Exercise> {
        // 필터 리스트 생성
        val filters = mutableListOf<ExerciseFilter>()

        // null이 아닌 경우만 필터 추가 (?.let 사용)
        category?.let { filters.add(CategoryFilter(it)) }
        difficulty?.let { filters.add(DifficultyFilter(it)) }
        targetMuscle?.let { filters.add(TargetMuscleFilter(it)) }

        return when {
            filters.isEmpty() -> repository.getAllExercises()
            else -> {
                val compositeFilter = CompositeFilter(filters)
                repository.searchExercises(compositeFilter)
            }
        }
    }
}