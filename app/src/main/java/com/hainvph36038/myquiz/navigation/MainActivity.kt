package com.hainvph36038.myquiz.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.hainvph36038.myquiz.MainScreen
import com.hainvph36038.myquiz.screens.quiz.QuizScreen
import com.hainvph36038.myquiz.model.Quiz
import com.hainvph36038.myquiz.ui.theme.MyQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyQuizTheme {
                // Khai báo biến trạng thái để lưu trữ quiz đã chọn và tên người dùng.
                var selectedQuiz by remember { mutableStateOf<Quiz?>(null) }
                var userName by remember { mutableStateOf("") }
                // Điều kiện để xác định màn hình nào sẽ được hiển thị
                if (selectedQuiz != null && userName.isNotBlank()) {
                    QuizScreen(
                        quiz = selectedQuiz!!,// Truyền quiz đã chọn vào QuizScreen
                        userName = userName   // Truyền tên người dùng vào QuizScreen
                    ) {
                        selectedQuiz = null// Đặt lại quiz đã chọn
                        userName = ""      // Đặt lại tên người dùng
                    }
                } else {
                    // Nếu không có quiz đã chọn hoặc tên người dùng rỗng, hiển thị MainScreen
                    MainScreen(
                        onQuizSelected = { quiz, name ->
                            selectedQuiz = quiz// Cập nhật quiz đã chọn
                            userName = name    // Cập nhật tên người dùng
                        }
                    )
                }
            }
        }
    }
}
