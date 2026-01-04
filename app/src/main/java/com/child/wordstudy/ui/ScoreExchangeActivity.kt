package com.child.wordstudy.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.child.wordstudy.R
import com.child.wordstudy.utils.ScoreManager

class ScoreExchangeActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_exchange)

        // 兑换奖励按钮
        findViewById<Button>(R.id.btn_exchange_reward).setOnClickListener {
            if (ScoreManager.canExchangeReward()) {
                if (ScoreManager.exchangeReward()) {
                    android.widget.Toast.makeText(this, "兑换成功！", android.widget.Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                android.widget.Toast.makeText(this, "积分不足，需要10积分", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        // 返回按钮
        findViewById<Button>(R.id.btn_back_to_score).setOnClickListener {
            finish()
        }
    }
}