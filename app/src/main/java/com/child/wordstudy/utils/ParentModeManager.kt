package com.child.wordstudy.utils

import android.content.Context
import android.content.SharedPreferences

object ParentModeManager {
    
    private const val PREFS_NAME = "parent_mode_prefs"
    private const val KEY_TODAY_LEARN_TIME = "today_learn_time"
    private const val KEY_DAILY_MAX_TIME = "daily_max_time"
    private const val KEY_PARENT_PASSWORD = "parent_password"
    private const val KEY_PARENT_MODE_ENABLED = "parent_mode_enabled"
    
    private var preferences: SharedPreferences? = null
    
    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // 设置默认值
        if (preferences?.getInt(KEY_DAILY_MAX_TIME, -1) == -1) {
            preferences?.edit()
                ?.putInt(KEY_DAILY_MAX_TIME, 30) // 默认每天最大学习时间30分钟
                ?.putString(KEY_PARENT_PASSWORD, "1234") // 默认密码
                ?.putBoolean(KEY_PARENT_MODE_ENABLED, true)
                ?.apply()
        }
    }
    
    fun addTodayLearnTime(seconds: Int) {
        val currentTime = preferences?.getInt(KEY_TODAY_LEARN_TIME, 0) ?: 0
        preferences?.edit()
            ?.putInt(KEY_TODAY_LEARN_TIME, currentTime + seconds)
            ?.apply()
    }
    
    fun getTodayLearnTime(): Int {
        return preferences?.getInt(KEY_TODAY_LEARN_TIME, 0) ?: 0
    }
    
    fun getDailyMaxTime(): Int {
        return preferences?.getInt(KEY_DAILY_MAX_TIME, 30) ?: 30
    }
    
    fun setDailyMaxTime(minutes: Int) {
        preferences?.edit()
            ?.putInt(KEY_DAILY_MAX_TIME, minutes)
            ?.apply()
    }
    
    fun isParentModeEnabled(): Boolean {
        return preferences?.getBoolean(KEY_PARENT_MODE_ENABLED, true) ?: true
    }
    
    fun setParentModeEnabled(enabled: Boolean) {
        preferences?.edit()
            ?.putBoolean(KEY_PARENT_MODE_ENABLED, enabled)
            ?.apply()
    }
    
    fun verifyParentPassword(password: String): Boolean {
        val savedPassword = preferences?.getString(KEY_PARENT_PASSWORD, "1234") ?: "1234"
        return password == savedPassword
    }
    
    fun changeParentPassword(newPassword: String) {
        preferences?.edit()
            ?.putString(KEY_PARENT_PASSWORD, newPassword)
            ?.apply()
    }
    
    fun resetTodayLearnTime() {
        preferences?.edit()
            ?.putInt(KEY_TODAY_LEARN_TIME, 0)
            ?.apply()
    }
    
    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "${minutes}分${remainingSeconds}秒"
    }
}