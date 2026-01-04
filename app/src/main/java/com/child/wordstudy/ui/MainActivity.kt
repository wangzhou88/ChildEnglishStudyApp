package com.child.wordstudy.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.child.wordstudy.R

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // 初始化管理器
        ScoreManager.init(this)
        ParentModeManager.init(this)
        
        // 语法学习按钮
        findViewById<Button>(R.id.btn_grammar_learning).setOnClickListener {
            val intent = Intent(this, GrammarBasicActivity::class.java)
            startActivity(intent)
        }
        
        // 专项练习按钮
        findViewById<Button>(R.id.btn_special_practice).setOnClickListener {
            val intent = Intent(this, SpecialPracticeActivity::class.java)
            startActivity(intent)
        }
        
        // 积分系统按钮
        findViewById<Button>(R.id.btn_score_system).setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
        
        // 家长模式按钮
        findViewById<Button>(R.id.btn_parent_mode).setOnClickListener {
            val intent = Intent(this, ParentModeActivity::class.java)
            startActivity(intent)
        }
    }
}