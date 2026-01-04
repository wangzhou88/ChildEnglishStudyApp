package com.child.wordstudy.model

data class Reward(
    val id: Int,
    val name: String,
    val description: String,
    val costScore: Int,
    val iconResId: Int = android.R.drawable.ic_menu_help
)