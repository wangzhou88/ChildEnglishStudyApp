package com.child.wordstudy.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.child.wordstudy.R
import com.child.wordstudy.utils.ParentModeManager

class ParentModeActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_mode)

        // 显示当前学习时长
        updateLearnTimeDisplay()

        // 设置最大学习时间按钮
        findViewById<Button>(R.id.btn_set_max_time).setOnClickListener {
            showSetMaxTimeDialog()
        }

        // 重置今日学习时长按钮
        findViewById<Button>(R.id.btn_reset_time).setOnClickListener {
            ParentModeManager.resetTodayLearnTime()
            updateLearnTimeDisplay()
            android.widget.Toast.makeText(this, "今日学习时长已重置", android.widget.Toast.LENGTH_SHORT).show()
        }

        // 修改家长密码按钮
        findViewById<Button>(R.id.btn_change_password).setOnClickListener {
            showChangePasswordDialog()
        }

        // 返回按钮
        findViewById<Button>(R.id.btn_back_to_main).setOnClickListener {
            finish()
        }
    }

    private fun updateLearnTimeDisplay() {
        val todayTime = ParentModeManager.getTodayLearnTime()
        val maxTime = ParentModeManager.getDailyMaxTime() * 60
        
        findViewById<android.widget.TextView>(R.id.tv_today_time).text = "今日学习时长：${ParentModeManager.formatTime(todayTime)}"
        findViewById<android.widget.TextView>(R.id.tv_max_time).text = "每日最大学习时长：${maxTime / 60}分钟"
    }

    private fun showSetMaxTimeDialog() {
        val input = EditText(this)
        input.setHint("请输入分钟数")
        
        AlertDialog.Builder(this)
            .setTitle("设置每日最大学习时间")
            .setView(input)
            .setPositiveButton("确认") { _, _ ->
                val minutes = input.text.toString().toIntOrNull()
                if (minutes != null && minutes > 0) {
                    ParentModeManager.setDailyMaxTime(minutes)
                    updateLearnTimeDisplay()
                    android.widget.Toast.makeText(this, "设置成功", android.widget.Toast.LENGTH_SHORT).show()
                } else {
                    android.widget.Toast.makeText(this, "请输入有效的分钟数", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun showChangePasswordDialog() {
        val input = EditText(this)
        input.setHint("请输入新密码")
        
        AlertDialog.Builder(this)
            .setTitle("修改家长密码")
            .setView(input)
            .setPositiveButton("确认") { _, _ ->
                val newPassword = input.text.toString()
                if (newPassword.isNotEmpty()) {
                    ParentModeManager.changeParentPassword(newPassword)
                    android.widget.Toast.makeText(this, "密码修改成功", android.widget.Toast.LENGTH_SHORT).show()
                } else {
                    android.widget.Toast.makeText(this, "密码不能为空", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }
}