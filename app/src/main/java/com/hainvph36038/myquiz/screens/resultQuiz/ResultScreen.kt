package com.hainvph36038.myquiz.screens.resultQuiz


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hainvph36038.myquiz.QuizPreferences
import com.hainvph36038.myquiz.model.Quiz

@Composable
fun ResultScreen() {
    val context = LocalContext.current
    // Tạo đối tượng QuizPreferences để truy cập và lưu trữ dữ liệu quiz
    val quizPreferences = remember { QuizPreferences(context) }
    // Lấy danh sách quiz từ QuizPreferences
    val quizzes = quizPreferences.getQuizzes()

    // Cấu trúc giao diện kết quả quiz
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Kết quả Quiz", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị danh sách các kết quả quiz
        LazyColumn {
            // Đối với mỗi quiz, lấy kết quả và hiển thị
            items(quizzes) { quiz ->
                // Lấy kết quả cho quiz hiện tại
                val results = quizPreferences.getQuizResults(quiz.id)
                // Hiển thị từng kết quả
                results.forEach { result ->
                    ResultItem(quiz = quiz, userName = result.userName, score = result.score, correctAnswers = result.correctAnswers, totalQuestions = result.totalQuestions)
                }
            }
        }
    }
}

@Composable
fun ResultItem(quiz: Quiz, userName: String, score: Int, correctAnswers: Int, totalQuestions: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Hiển thị thông tin về kết quả của người dùng
        Text(
            text = "$userName - ${quiz.title}: $score điểm, Câu trả lời đúng: $correctAnswers/$totalQuestions",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1f)
        )
    }
}
