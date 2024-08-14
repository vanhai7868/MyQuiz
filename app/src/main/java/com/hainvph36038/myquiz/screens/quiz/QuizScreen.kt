package com.hainvph36038.myquiz.screens.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hainvph36038.myquiz.QuizPreferences
import com.hainvph36038.myquiz.model.Quiz

@Composable
fun QuizScreen(quiz: Quiz, userName: String, onQuizCompleted: () -> Unit) {
    val context = LocalContext.current
    // Tạo đối tượng QuizPreferences để truy cập và lưu trữ dữ liệu quiz
    val quizPreferences = remember { QuizPreferences(context) }

    // Khai báo các biến trạng thái cho câu hỏi hiện tại, tùy chọn đã chọn, điểm số và số câu trả lời đúng
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }

    // Kiểm tra xem có còn câu hỏi để hiển thị không
    if (currentQuestionIndex < quiz.questions.size) {
        val question = quiz.questions[currentQuestionIndex]
        Column(modifier = Modifier.padding(16.dp)) {
            // Hiển thị lời chào và câu hỏi hiện tại
            Text(text = "Hello $userName", style = MaterialTheme.typography.h6)
            Text(text = question.questionText, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            // Hiển thị các tùy chọn câu trả lời dưới dạng radio buttons
            question.options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedOption = index }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == index,
                        onClick = { selectedOption = index }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị các tùy chọn câu trả lời dưới dạng radio buttons
            Button(
                onClick = {
                    // Kiểm tra câu trả lời và cập nhật điểm số nếu câu trả lời đúng
                    if (selectedOption == question.correctAnswerIndex) {
                        score++
                        correctAnswers++
                    }
                    // Đặt tùy chọn đã chọn về -1 và chuyển đến câu hỏi tiếp theo
                    selectedOption = -1
                    currentQuestionIndex++
                },
                // Kích hoạt nút chỉ khi đã chọn một tùy chọn
                enabled = selectedOption != -1
            ) {
                Text("Tiếp")
            }
        }
    } else {
        // Nếu không còn câu hỏi nào để hiển thị, hiển thị kết quả
        val totalQuestions = quiz.questions.size
        val maxPoints = 10// Điểm tối đa của bài kiểm tra
        val pointsPerQuestion = maxPoints / totalQuestions
        val finalScore = pointsPerQuestion * correctAnswers// Tính điểm cuối cùng

        Column(modifier = Modifier.padding(16.dp)) {
            // Hiển thị điểm số và số câu trả lời đúng
            Text(text = "Điểm của bạn: $finalScore/$maxPoints", style = MaterialTheme.typography.h6)
            Text(text = "Câu trả lời đúng: $correctAnswers/$totalQuestions", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(16.dp))

            // Nút để kết thúc quiz và lưu kết quả
            Button(onClick = {
                quizPreferences.saveQuizResults(quiz.id, userName, finalScore, correctAnswers, totalQuestions)
                onQuizCompleted()// Gọi callback khi quiz hoàn tất
            }) {
                Text("Kết thúc")
            }
        }
    }
}
