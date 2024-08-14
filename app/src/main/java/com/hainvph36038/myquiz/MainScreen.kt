package com.hainvph36038.myquiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hainvph36038.myquiz.model.Quiz
import com.hainvph36038.myquiz.screens.listQuiz.QuizListItem
import com.hainvph36038.myquiz.screens.resultQuiz.ResultScreen

@Composable
fun MainScreen(onQuizSelected: (Quiz, String) -> Unit) {
    val context = LocalContext.current
    // Tạo đối tượng QuizPreferences để truy cập và lưu trữ quiz
    val quizPreferences = remember { QuizPreferences(context) }

    // Khai báo các biến trạng thái để lưu trữ danh sách quiz, trạng thái các màn hình và quiz đã chọn
    var quizzes by remember { mutableStateOf(quizPreferences.getQuizzes()) }
    var showCreateQuiz by remember { mutableStateOf(false) }
    var showResults by remember { mutableStateOf(false) }
    var selectedQuiz by remember { mutableStateOf<Quiz?>(null) }
    var showEnterName by remember { mutableStateOf(false) }

    // Điều kiện để hiển thị màn hình nhập tên người dùng
    if (showEnterName && selectedQuiz != null) {
        EnterNameScreen { name ->
            // Khi người dùng nhập tên và nhấn nút, gọi callback onQuizSelected và ẩn màn hình nhập tên
            onQuizSelected(selectedQuiz!!, name)
            showEnterName = false
        }
        // Điều kiện để hiển thị màn hình tạo quiz mới
    } else if (showCreateQuiz) {
        CreateQuizScreen(onCreateQuiz = {
            // Cập nhật danh sách quiz sau khi tạo quiz mới và ẩn màn hình tạo quiz
            quizzes = quizPreferences.getQuizzes()
            showCreateQuiz = false
        })
        // Điều kiện để hiển thị màn hình kiểm tra kết quả
    } else if (showResults) {
        ResultScreen()
        // Nếu không hiển thị các màn hình khác, hiển thị giao diện chính
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            // Tiêu đề của màn hình chính
            Text(text = "Quizz", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị danh sách quiz
            LazyColumn {
                items(quizzes) { quiz ->
                    // Hiển thị từng quiz dưới dạng mục danh sách với sự kiện nhấp chuột để chọn quiz
                    QuizListItem(quiz = quiz, onClick = {
                        selectedQuiz = quiz
                        showEnterName = true
                    })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Nút để hiển thị màn hình tạo quiz mới
            Button(onClick = { showCreateQuiz = true }) {
                Text("Tạo Quiz mới")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Nút để hiển thị màn hình kiểm tra kết quả
            Button(onClick = { showResults = true }) {
                Text("Kiểm tra kết quả")
            }
        }
    }
}
