package com.hainvph36038.myquiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hainvph36038.myquiz.model.Question

@Composable
fun AddQuestionScreen(onAddQuestion: (Question) -> Unit) {
    // Khai báo các biến trạng thái để lưu trữ thông tin câu hỏi, các tùy chọn, và chỉ số đáp án đúng
    var questionText by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(listOf("", "", "", "")) }
    var correctAnswerIndex by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Tiêu đề của màn hình
        Text(text = "Thêm câu hỏi mới", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        // Trường nhập văn bản cho câu hỏi
        OutlinedTextField(
            value = questionText,
            onValueChange = { questionText = it },
            label = { Text("Câu hỏi") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị các trường nhập văn bản cho các tùy chọn câu trả lời
        options.forEachIndexed { index, option ->
            OutlinedTextField(
                value = option,
                onValueChange = { newOption ->
                    // Cập nhật tùy chọn câu trả lời tại vị trí tương ứng
                    options = options.toMutableList().apply { set(index, newOption) }
                },
                label = { Text("Câu ${index + 1}") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị các radio button cho các tùy chọn và cho phép chọn đáp án đúng
        (0 until options.size).forEach { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { correctAnswerIndex = index }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Radio button cho mỗi tùy chọn
                RadioButton(
                    selected = correctAnswerIndex == index,
                    onClick = { correctAnswerIndex = index }
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Hiển thị văn bản cho mỗi tùy chọn
                Text(text = options[index])
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button để thêm câu hỏi mới
        Button(
            onClick = {
                // Nếu câu hỏi và đáp án đúng đã được chọn, tạo đối tượng câu hỏi mới và gọi callback
                if (questionText.isNotBlank() && correctAnswerIndex != -1) {
                    val newQuestion = Question(
                        id = (1..1000).random(),// Tạo ID ngẫu nhiên cho câu hỏi
                        questionText = questionText,
                        options = options,
                        correctAnswerIndex = correctAnswerIndex
                    )
                    onAddQuestion(newQuestion) // Gọi callback với câu hỏi mới
                }
            },
            // Kích hoạt nút chỉ khi câu hỏi và đáp án đúng đã được chọn
            enabled = questionText.isNotBlank() && correctAnswerIndex != -1
        ) {
            Text("Thêm câu hỏi")
        }
    }
}
