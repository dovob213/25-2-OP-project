package data

import model.Exercise

/* 파이어베이스에 실제 데이터 입력 구현 이전에,
    가라로 만들어 둔 샘플 데이터 파일입니다. 개발 때 테스트용. 나중에 지울 듯
 */

class MockExerciseRepository : ExerciseRepository {
    // 메모리에만 저장. 앱 끄면 사라지게

    private val exercises = mutableListOf<Exercise>()

    // 샘플 데이터 미리 넣어두겟습니다. 추가하면서 테스트 하다, 나중에 파이어베이스 구현 후 제거 예정이요
    init {
        exercises.add(Exercise("ex001", "벤치프레스", "가슴", "가슴 운동 근본"))
        exercises.add(Exercise("ex002", "스쿼트", "하체", "하체 운동 지존"))
        exercises.add(Exercise("ex003", "데드리프트", "하체", "하체와 등 전체를 발달시킴"))
        exercises.add(Exercise("ex004", "숄더프레스", "어깨", "어깨 앞/삼두근 발달"))
        exercises.add(Exercise("ex005", "덤벨프레스", "가슴", "벤치프레스 보조 운동"))
        exercises.add(Exercise("ex006", "레그프레스", "하체", "스쿼트 보조 운동"))
        exercises.add(Exercise("ex007", "시티드로우", "등", "등 운동"))
    }

    override suspend fun getExercise(id: String): Exercise? {
        return exercises.find { it.id == id }
    }

    override suspend fun getAllExercises(): List<Exercise> {
        return exercises.toList() // 복사본 반환
    }

    override suspend fun saveExercise(exercise: Exercise) {
        // 같은 ID가 있으면 업데이트, 없으면 추가
        val index = exercises.indexOfFirst { it.id == exercise.id }
        if (index != -1) {
            exercises[index] = exercise
        } else {
            exercises.add(exercise)
        }
    }
}