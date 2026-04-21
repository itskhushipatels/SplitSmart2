package com.example.splitsmart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.splitsmart.model.Expense

class ExpenseViewModel : ViewModel() {

    companion object {
        private val _expenseList = MutableLiveData<MutableList<Expense>>(mutableListOf())
        val expenseList: LiveData<MutableList<Expense>> = _expenseList
    }

    fun addExpense(expense: Expense) {
        val updatedList = _expenseList.value ?: mutableListOf()
        updatedList.add(expense)
        _expenseList.value = ArrayList(updatedList) // 🔥 force refresh
    }
}