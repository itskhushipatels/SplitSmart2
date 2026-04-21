package com.example.splitsmart.model

import java.io.Serializable

data class Expense(
    val title: String,
    val amount: Double,
    val paidBy: String,
    val members: List<String>
) : Serializable