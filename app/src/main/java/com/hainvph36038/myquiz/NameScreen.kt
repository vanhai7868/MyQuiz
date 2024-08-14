package com.hainvph36038.myquiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EnterNameScreen(onNameEntered: (String) -> Unit) {
    // Khai báo biến trạng thái để lưu tên người dùng
    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        // Tiêu đề màn hình
        Text(text = "Nhập tên của bạn", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        // Trường nhập tên người dùng
        OutlinedTextField(
            value = name,// Trường nhập tên người dùng
            onValueChange = { name = it },// Cập nhật giá trị khi người dùng nhập
            label = { Text("Tên") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nút để bắt đầu quiz
        Button(
            onClick = {
                if (name.isNotBlank()) {
                    onNameEntered(name)// Gọi callback khi nút được nhấn và tên không rỗng
                }
            },
            enabled = name.isNotBlank()// Gọi callback khi nút được nhấn và tên không rỗng
        ) {
            Text("Bắt đầu")
        }
    }
}
