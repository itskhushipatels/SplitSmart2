package com.example.splitsmart.ui

import android.os.Bundle
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

            val title = titleInput.text.toString().trim()
            val amountText = amountInput.text.toString().trim()
            val paidBy = paidByInput.text.toString().trim()
            val membersText = membersInput.text.toString().trim()

            val amount = amountText.toDoubleOrNull()
            val members = membersText.split(",").map { it.trim() }


            if (title.isEmpty() || amount == null || paidBy.isEmpty() || membersText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val expense = Expense(
                title = title,
                amount = amount,
                paidBy = paidBy,
                members = members
            )

            viewModel.addExpense(expense)

            Toast.makeText(this, "Expense Added & Split!", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}