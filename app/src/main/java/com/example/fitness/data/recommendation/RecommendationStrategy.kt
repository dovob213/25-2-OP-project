package com.example.fitness.data.recommendation

import com.example.fitness.model.Exercise

/**
 * 추천 전략 인터페이스
 * - 다양한 추천 알고리즘을 통일된 방법으로 사용
 */
interface RecommendationStrategy {
    fun recommend(exercises: List<Exercise>, limit: Int): List<Exercise>
}