data class Student(val name: String, val age: Int, val scores: List<Double>)

fun averageScore(student: Student): Double {
    if (student.scores.isEmpty()) {
        return 0.0
    }

    var sum = 0.0
    for (score in student.scores) {
        sum += score
    }

    val averagescore = sum / student.scores.size
    val rounded = (averagescore * 100).toInt() / 100.0
    return rounded
}

fun topStudent(students: List<Student>): Student? {
    if (students.isEmpty()) {
        return null
    }

    var maxStudent = students[0]
    var maxAverage = averageScore(maxStudent)

    for (i in 1 until students.size) {
        val avg = averageScore(students[i])

        if (avg > maxAverage) {
            maxAverage = avg
            maxStudent = students[i]
        }
    }

    return maxStudent
}

fun groupStudent(students: List<Student>, passScore: Double = 5.0): Map<Boolean, List<Student>> {

    val passList = mutableListOf<Student>()
    val failList = mutableListOf<Student>()

    for (student in students) {
        if (averageScore(student) >= passScore) {
            passList.add(student)
        } else {
            failList.add(student)
        }
    }

    return mapOf(
        true to passList,
        false to failList
    )
}

fun main() {

    val students = listOf(
        Student("Sv1", 20, listOf(8.4, 9.2, 7.0)),
        Student("Sv2", 21, listOf(6.5, 5.8, 7.5)),
        Student("Sv3", 19, listOf(4.5, 3.4, 5.5)),
        Student("Sv4", 22, listOf()),
        Student("Sv5", 20, listOf(9.0, 10.0, 9.5))
    )

    println("Diem trung binh")
    for (student in students) {
        println("${student.name}: ${averageScore(student)}")
    }

    val top = topStudent(students)
    if (top != null) {
        println("Sinh vien co diem TB cao nhat:")
        println("${top.name}: ${averageScore(top)}")
    }

    val result = groupStudent(students)
    println("Danh sach dau:")
    for (student in result[true]!!) {
        println("${student.name}: ${averageScore(student)}")
    }

    println("Danh sach rot:")
    for (student in result[false]!!) {
        println("${student.name}: ${averageScore(student)}")
    }
}