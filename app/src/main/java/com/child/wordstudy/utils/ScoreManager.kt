package com.child.wordstudy.utils

import android.content.Context
import android.content.SharedPreferences

object ScoreManager {
    
    enum class ScoreType {
        GRAMMAR_STUDY,
        WORD_LEARNING,
        SPECIAL_PRACTICE,
        DAILY_REWARD
    }
    
    private const val PREFS_NAME = "score_prefs"
    private const val KEY_TOTAL_SCORE = "total_score"
    private const val KEY_DAILY_SCORE = "daily_score"
    
    private var preferences: SharedPreferences? = null
    
    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    fun addScore(type: ScoreType) {
        val scoreValue = when (type) {
            ScoreType.GRAMMAR_STUDY -> 6
            ScoreType.WORD_LEARNING -> 5
            ScoreType.SPECIAL_PRACTICE -> 8
            ScoreType.DAILY_REWARD -> 3
        }
        
        val currentTotal = preferences?.getInt(KEY_TOTAL_SCORE, 0) ?: 0
        val currentDaily = preferences?.getInt(KEY_DAILY_SCORE, 0) ?: 0
        
        preferences?.edit()
            ?.putInt(KEY_TOTAL_SCORE, currentTotal + scoreValue)
            ?.putInt(KEY_DAILY_SCORE, currentDaily + scoreValue)
            ?.apply()
    }
    
    fun getTotalScore(): Int {
        return preferences?.getInt(KEY_TOTAL_SCORE, 0) ?: 0
    }
    
    fun getDailyScore(): Int {
        return preferences?.getInt(KEY_DAILY_SCORE, 0) ?: 0
    }
    
    fun canExchangeReward(): Boolean {
        return getTotalScore() >= 10
    }
    
    fun exchangeReward(): Boolean {
        return if (canExchangeReward()) {
            val currentTotal = getTotalScore()
            preferences?.edit()
                ?.putInt(KEY_TOTAL_SCORE, currentTotal - 10)
                ?.apply()
            true
        } else {
            false
        }
    }
    
    fun resetDailyScore() {
        preferences?.edit()
            ?.putInt(KEY_DAILY_SCORE, 0)
            ?.apply()
    }
}