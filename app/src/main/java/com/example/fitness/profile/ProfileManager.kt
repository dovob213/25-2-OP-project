package com.yourteam.fitnessapp.ui.profile

import com.yourteam.fitnessapp.data.UserProfileRepository
import com.yourteam.fitnessapp.model.UserProfile

class ProfileManager(
    private val profileRepository: UserProfileRepository
) {

    suspend fun getProfile(userId: String): UserProfile? {
        return profileRepository.getUserProfile(userId)
    }

    suspend fun saveProfile(profile: UserProfile) {
        profileRepository.saveUserProfile(profile)
    }

    suspend fun updateProfile(profile: UserProfile) {
        profileRepository.updateUserProfile(profile)
    }

    fun calculateBMI(heightCm: Double, weightKg: Double): Double {
        if (heightCm <= 0 || weightKg <= 0) return 0.0
        val heightM = heightCm / 100.0
        return weightKg / (heightM * heightM)
    }

    fun getIdealWeight(heightCm: Double, gender: String): Double {
        if (heightCm <= 0) return 0.0
        val heightM = heightCm / 100.0

        // 표준체중 = (키(m) x 키(m)) x 22 (남자) / 21 (여자)
        val multiplier = if (gender == "male") 22.0 else 21.0
        return heightM * heightM * multiplier
    }
}