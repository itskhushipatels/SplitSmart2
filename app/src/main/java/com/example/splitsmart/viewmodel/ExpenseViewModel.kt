package com.example.splitsmart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.splitsmart.model.Expense

class ExpenseViewModel : ViewModel() {

    companion object {
        val expenseList = MutableLiveData<MutableList<Expense>>(mutableListOf())
    }

    fun addExpense(expense: Expense) {
        val currentList = expenseList.value ?: mutableListOf()
        currentList.add(expense)
        expenseList.value = currentList
    }
}