package com.child.wordstudy.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.child.wordstudy.R

class SpecialPracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_practice)

        // 词汇练习按钮
        findViewById<Button>(R.id.btn_practice_vocabulary).setOnClickListener {
            // TODO: 实现词汇练习功能
            android.widget.Toast.makeText(this, "词汇练习功能开发中", android.widget.Toast.LENGTH_SHORT).show()
        }

        // 语法练习按钮
        findViewById<Button>(R.id.btn_practice_grammar).setOnClickListener {
            // TODO: 实现语法练习功能
            android.widget.Toast.makeText(this, "语法练习功能开发中", android.widget.Toast.LENGTH_SHORT).show()
        }

        // 阅读练习按钮
        findViewById<Button>(R.id.btn_practice_reading).setOnClickListener {
            // TODO: 实现阅读练习功能
            android.widget.Toast.makeText(this, "阅读练习功能开发中", android.widget.Toast.LENGTH_SHORT).show()
        }

        // 返回按钮
        findViewById<Button>(R.id.btn_back_to_main).setOnClickListener {
            finish()
        }
    }
}