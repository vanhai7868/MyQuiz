package com.hainvph36038.myquiz.screens.listQuiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hainvph36038.myquiz.R
import com.hainvph36038.myquiz.model.Quiz

@Composable
fun QuizListItem(quiz: Quiz, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(10.dp)
            .height(110.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f), // Màu viền
                shape = RoundedCornerShape(8.dp) // Viền bo góc
            )
            .clip(RoundedCornerShape(8.dp)), // Bo góc cho toàn bộ phần tử
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Thêm phần nội dung với hình ảnh và text
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = quiz.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp) // Khoảng cách giữa text và ảnh
            )
            Text(
                text = "8 terms 13/08/2024", // Thay thế bằng tên bạn muốn hiển thị
                style = MaterialTheme.typography.body2
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hình ảnh
                Image(
                    painter = painterResource(id = R.drawable.avatar), // Thay thế bằng ID của hình ảnh của bạn
                    contentDescription = "Tên ảnh",
                    modifier = Modifier
                        .size(40.dp) // Kích thước của hình ảnh
                        .clip(CircleShape) // Bo góc hình ảnh nếu cần
                )
                Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa ảnh và text bên cạnh
                // Text bên cạnh hình ảnh
                Text(
                    text = "hainvph36038", // Thay thế bằng tên bạn muốn hiển thị
                    style = MaterialTheme.typography.body2.copy(color = Color.Blue)
                )
            }
        }
    }
}
