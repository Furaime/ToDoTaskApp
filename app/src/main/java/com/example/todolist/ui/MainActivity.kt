package com.example.todolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.datasource.TaskDataSource

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter

        insertListeners()
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }

        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent. putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(Intent, CREATE_NEW_TASK)

        }
        adapter.listenerDelete = {
            Log.e("TAG", "ListenerDelete: $it")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {

        }
    }

    private updateList() {
        adapter.submitList(TaskDataSource.getList())
    }

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }

    }
}