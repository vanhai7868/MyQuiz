package com.hainvph36038.myquiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hainvph36038.myquiz.model.Question
import com.hainvph36038.myquiz.model.Quiz


@Composable
fun CreateQuizScreen(onCreateQuiz: () -> Unit) {
    val context = LocalContext.current
    // Tạo đối tượng QuizPreferences để lưu trữ quiz
    val quizPreferences = remember { QuizPreferences(context) }

    // Khai báo các biến trạng thái để lưu trữ tiêu đề quiz, danh sách câu hỏi và trạng thái hiển thị màn hình thêm câu hỏi
    var quizTitle by remember { mutableStateOf("") }
    var questions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var showAddQuestion by remember { mutableStateOf(false) }

    // Hiển thị màn hình thêm câu hỏi nếu showAddQuestion là true
    if (showAddQuestion) {
        AddQuestionScreen(onAddQuestion = { newQuestion ->
            // Thêm câu hỏi mới vào danh sách câu hỏi và ẩn màn hình thêm câu hỏi
            questions = questions + newQuestion
            showAddQuestion = false
        })
    } else {
        // Nếu không hiển thị màn hình thêm câu hỏi, hiển thị giao diện tạo quiz
        Column(modifier = Modifier.padding(16.dp)) {
            // Tiêu đề của màn hình
            Text(text = "Tạo bài Quiz mới", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            // Trường nhập văn bản cho tiêu đề quiz
            OutlinedTextField(
                value = quizTitle,
                onValueChange = { quizTitle = it },
                label = { Text("Tiêu đề Quiz") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút để hiển thị màn hình thêm câu hỏi
            Button(onClick = { showAddQuestion = true }) {
                Text("Thêm câu hỏi")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị danh sách câu hỏi hiện có
            LazyColumn {
                items(questions) { question ->
                    Text(text = question.questionText)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nút để tạo quiz mới
            Button(
                onClick = {
                    // Nếu tiêu đề quiz và danh sách câu hỏi không rỗng, tạo quiz mới và lưu vào QuizPreferences
                    if (quizTitle.isNotBlank() && questions.isNotEmpty()) {
                        val newQuiz = Quiz(
                            id = (1..1000).random(),// Tạo ID ngẫu nhiên cho quiz
                            title = quizTitle,
                            questions = questions
                        )
                        quizPreferences.saveQuiz(newQuiz)// Lưu quiz vào QuizPreferences
                        onCreateQuiz()// Gọi callback để thông báo rằng quiz đã được tạo
                    }
                },
                enabled = quizTitle.isNotBlank() && questions.isNotEmpty()// Kích hoạt nút chỉ khi tiêu đề quiz và danh sách câu hỏi đã được nhập đầy đủ
            ) {
                Text("Tạo Quiz")
            }
        }
    }
}
