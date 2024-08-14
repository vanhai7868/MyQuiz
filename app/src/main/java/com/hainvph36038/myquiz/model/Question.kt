package com.hainvph36038.myquiz.model

data class Question(
    val id: Int,
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
