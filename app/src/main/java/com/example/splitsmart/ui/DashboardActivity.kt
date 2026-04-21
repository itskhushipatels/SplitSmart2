package com.example.splitsmart.ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.splitsmart.R
import com.example.splitsmart.viewmodel.ExpenseViewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        listView = findViewById(R.id.expenseList)
        val addBtn = findViewById<Button>(R.id.addExpenseBtn)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        ExpenseViewModel.expenseList.observe(this) { list ->
            adapter.clear()
            adapter.addAll(list.map { "${it.title} - $${it.amount}" })
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val expense = ExpenseViewModel.expenseList.value?.getOrNull(position)

            if (expense != null) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("expense", expense)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error loading expense", Toast.LENGTH_SHORT).show()
            }
        }

        addBtn.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }
    }
}