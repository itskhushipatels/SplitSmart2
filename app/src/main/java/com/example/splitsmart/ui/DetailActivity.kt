package com.example.splitsmart.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.splitsmart.R
import com.example.splitsmart.model.Expense

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val expense = intent.getSerializableExtra("expense") as? Expense

        if (expense == null) {
            finish()
            return
        }

        val titleText = findViewById<TextView>(R.id.titleText)
        val paidByText = findViewById<TextView>(R.id.paidByText)
        val membersText = findViewById<TextView>(R.id.membersText)
        val splitText = findViewById<TextView>(R.id.splitText)

        titleText.text = "Expense: ${expense.title}"
        paidByText.text = "Paid by: ${expense.paidBy}"
        membersText.text = "Members: ${expense.members.joinToString(", ")}"

        val total = expense.amount
        val peopleCount = expense.members.size + 1
        val split = total / peopleCount

        val result = buildString {
            append("Each person pays: $%.2f\n\n".format(split))
            expense.members.forEach {
                append("$it pays $%.2f\n".format(split))
            }
            append("\n${expense.paidBy} paid $%.2f".format(total))
        }

        splitText.text = result
    }
}