package com.example.splitsmart.model

data class Expense(
    val title: String,
    val amount: Double,
    val paidBy: String,
    val members: List<String>
)