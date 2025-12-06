package data

//package com.yourteam.fitnessapp.data

import com.yourteam.fitnessapp.model.UserProfile

interface UserProfileRepository {
    suspend fun getUserProfile(userId: String): UserProfile?
    suspend fun saveUserProfile(profile: UserProfile)
    suspend fun updateUserProfile(profile: UserProfile)
}