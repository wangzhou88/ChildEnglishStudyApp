package com.child.wordstudy.utils

import com.child.wordstudy.model.Grammar

object DataManager {
    
    private var grammarList: List<Grammar> = emptyList()
    
    fun getGrammarList(): List<Grammar> {
        if (grammarList.isEmpty()) {
            // 初始化一些示例语法数据
            grammarList = listOf(
                Grammar(
                    id = 1,
                    grammarTitle = "be动词的使用",
                    grammarContent = "be动词（am, is, are）用于描述状态或存在。\n\n" +
                            "用法：\n" +
                            "I am a student. (我是学生)\n" +
                            "She is beautiful. (她很漂亮)\n" +
                            "They are friends. (他们是朋友)",
                    imageResId = android.R.drawable.ic_menu_help
                ),
                Grammar(
                    id = 2,
                    grammarTitle = "一般现在时",
                    grammarContent = "一般现在时表示经常性、习惯性的动作或状态。\n\n" +
                            "用法：\n" +
                            "I go to school every day. (我每天去学校)\n" +
                            "She likes reading books. (她喜欢读书)",
                    imageResId = android.R.drawable.ic_menu_help
                ),
                Grammar(
                    id = 3,
                    grammarTitle = "现在进行时",
                    grammarContent = "现在进行时表示正在进行的动作。\n\n" +
                            "结构：be + 动词-ing\n\n" +
                            "用法：\n" +
                            "I am studying now. (我现在在学习)\n" +
                            "They are playing football. (他们在踢足球)",
                    imageResId = android.R.drawable.ic_menu_help
                )
            )
        }
        return grammarList
    }
    
    fun getWordList(): List<com.child.wordstudy.model.Word> {
        // 这里可以添加单词列表的实现
        return emptyList()
    }
}