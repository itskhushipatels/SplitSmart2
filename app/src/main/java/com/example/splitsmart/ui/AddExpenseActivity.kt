package com.example.splitsmart.ui

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.splitsmart.R
import com.example.splitsmart.model.Expense
import com.example.splitsmart.viewmodel.ExpenseViewModel

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var viewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        viewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val paidByInput = findViewById<EditText>(R.id.paidByInput)
        val membersInput = findViewById<EditText>(R.id.membersInput)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {

            Log.d("SplitSmart", "Save button clicked")

            val title = titleInput.text.toString().trim()
            val amount = amountInput.text.toString().toDoubleOrNull()
            val paidBy = paidByInput.text.toString().trim()
            val membersText = membersInput.text.toString().trim()


            if (title.isEmpty()) {
                Log.e("SplitSmart", "Validation failed: Title is empty")
                titleInput.error = "Required"
                return@setOnClickListener
            }

            if (amount == null || amount <= 0) {
                Log.e("SplitSmart", "Validation failed: Invalid amount")
                amountInput.error = "Enter valid amount"
                return@setOnClickListener
            }

            if (paidBy.isEmpty()) {
                Log.e("SplitSmart", "Validation failed: PaidBy is empty")
                paidByInput.error = "Required"
                return@setOnClickListener
            }

            if (membersText.isEmpty()) {
                Log.e("SplitSmart", "Validation failed: Members empty")
                membersInput.error = "Required"
                return@setOnClickListener
            }


            val members = membersText
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }

            val expense = Expense(title, amount, paidBy, members)

            viewModel.addExpense(expense)

            Log.d("SplitSmart", "Expense successfully added: $title")

            Toast.makeText(this, "Expense Added!", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}