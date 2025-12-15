package com.example.fitness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.fitness.data.RepositoryProvider
import com.example.fitness.model.Exercise
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Repository 초기화 (Phase 1: Mock, Phase 2: Firebase)
        initializeRepositories()

        // Navigation 설정
        setupNavigation()

        // Firestore 연동 테스트를 위한 임시 데이터 업로드
        uploadTestExercise()
    }

    private fun initializeRepositories() {
        // Phase 1: Mock 사용
        //RepositoryProvider.initializeWithMock()

        // Phase 2: Firebase 사용
        RepositoryProvider.initialize()
    }

    private fun setupNavigation() {
        // NavHostFragment를 통해 NavController 가져오기
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Bottom Navigation과 연결
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setupWithNavController(navController)
    }

    private fun uploadTestExercise() {
        lifecycleScope.launch {
            try {
                val repository = RepositoryProvider.getExerciseRepository()
                // 테스트용 데이터 생성
                val testExercise = Exercise(
                    id = "test_exercise_001",
                    name = "Test Push Up",
                    category = "Chest",
                    description = "Firestore 연동 테스트용 데이터입니다.",
                    level = 1.0,
                    tags = listOf("test", "bodyweight"),
                    location = "Home"
                )
                // Firestore에 저장
                repository.saveExercise(testExercise)
                println("✅ Test exercise uploaded successfully")
            } catch (e: Exception) {
                println("❌ Failed to upload test exercise: ${e.message}")
            }
        }
    }
}
