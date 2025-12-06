package data

import model.Routine
import model.RoutineExercise


/* 파이어베이스에 실제 데이터 입력 구현 이전에,
    가라로 만들어 둔 샘플 데이터 파일입니다. 개발 때 테스트용. 나중에 지울 듯
 */


class MockRoutineRepository : RoutineRepository {
    // 메모리에 저장 (앱 끄면 사라짐)
    private val routines = mutableListOf<Routine>()

    // 테스트용 가라 루틴 미리 넣기
    
    init {
        // "가슴 루틴" 루틴
        routines.add(
            Routine(
                id = "routine001",
                name = "가슴 루틴",
                exercises = listOf(
                    RoutineExercise(
                        exerciseId = "ex001",
                        targetSets = 3,
                        targetReps = 10,
                        targetWeight = 70.0
                    ),
                    RoutineExercise(
                        exerciseId = "ex005", // 덤벨프레스
                        targetSets = 3,
                        targetReps = 12,
                        targetWeight = 30.0
                    )
                )
            )
        )

        // "하체 루틴" 루틴
        routines.add(
            Routine(
                id = "routine002",
                name = "하체 루틴",
                exercises = listOf(
                    RoutineExercise(
                        exerciseId = "ex002", // 스쿼트
                        targetSets = 4,
                        targetReps = 8,
                        targetWeight = 100.0
                    ),
                    RoutineExercise(
                        exerciseId = "ex006", // 레그프레스
                        targetSets = 3,
                        targetReps = 12,
                        targetWeight = 150.0
                    )
                )
            )
        )

        // 전신 루틴
        routines.add(
            Routine(
                id = "routine003",
                name = "전신 루틴",
                exercises = listOf(
                    RoutineExercise(
                        exerciseId = "ex001", // 벤치프레스
                        targetSets = 3,
                        targetReps = 10,
                        targetWeight = 70.0
                    ),
                    RoutineExercise(
                        exerciseId = "ex002", // 스쿼트
                        targetSets = 3,
                        targetReps = 10,
                        targetWeight = 100.0
                    ),
                    RoutineExercise(
                        exerciseId = "ex007", // 시티드로우
                        targetSets = 4,
                        targetReps = 12,
                        targetWeight = 45.0
                    )
                )
            )
        )
    }

    // 루틴 1개 가져오기
    override suspend fun getRoutine(id: String): Routine? {
        return routines.find { it.id == id }
    }

    // 모든 루틴 가져오기
    override suspend fun getAllRoutines(): List<Routine> {
        return routines.toList() // 복사본 반환
    }

    // 루틴 저장하기
    override suspend fun saveRoutine(routine: Routine) {
        // 같은 ID가 있으면 업데이트, 없으면 추가
        val index = routines.indexOfFirst { it.id == routine.id }
        if (index != -1) {
            routines[index] = routine
            println("Mock: 루틴 업데이트 - ${routine.name}")
        } else {
            routines.add(routine)
            println("Mock: 루틴 추가 - ${routine.name}")
        }
    }

    // 루틴 삭제하기
    override suspend fun deleteRoutine(id: String) {
        routines.removeIf { it.id == id }
        println("Mock: 루틴 삭제 - $id")
    }
}


