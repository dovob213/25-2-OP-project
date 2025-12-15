package com.example.fitness.data.filter

import com.example.fitness.model.Exercise

/**
 * 필터 인터페이스
 * - 전략 패턴의 Strategy 역할
 * - 다양한 필터링 방법을 통일된 인터페이스로 제공
 */
interface ExerciseFilter {
    fun matches(exercise: Exercise): Boolean
}