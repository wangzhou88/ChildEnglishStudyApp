import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.child.wordstudy.R
import com.child.wordstudy.model.Grammar
import com.child.wordstudy.utils.DataManager
import com.child.wordstudy.utils.ParentModeManager
import com.child.wordstudy.utils.ScoreManager
import kotlinx.android.synthetic.main.activity_grammar_basic.*
import java.util.Timer
import java.util.TimerTask

class GrammarBasicActivity : AppCompatActivity() {

    private lateinit var grammarList: List<Grammar>
    private var currentIndex = 0
    private var grammarTimer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_basic)

        // 获取语法列表
        grammarList = DataManager.getGrammarList()
        if (grammarList.isEmpty()) {
            Toast.makeText(this, "暂无语法内容可学习", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // 初始化显示第一个语法知识点
        updateGrammarView()

        // 启动语法学习时长统计
        startGrammarTimeCount()

        // 上一个语法知识点
        btn_grammar_prev.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateGrammarView()
            } else {
                Toast.makeText(this, "已是第一个语法知识点", Toast.LENGTH_SHORT).show()
            }
        }

        // 下一个语法知识点
        btn_grammar_next.setOnClickListener {
            if (currentIndex < grammarList.size - 1) {
                currentIndex++
                updateGrammarView()
            } else {
                // 语法学习完成，增加积分
                ScoreManager.addScore(ScoreManager.ScoreType.GRAMMAR_STUDY)
                Toast.makeText(this, "语法学习完成！积分+6", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // 标记为已掌握
        btn_grammar_mastered.setOnClickListener {
            ScoreManager.addScore(ScoreManager.ScoreType.GRAMMAR_STUDY)
            Toast.makeText(this, "已标记为掌握！积分+6", Toast.LENGTH_SHORT).show()
            if (currentIndex < grammarList.size - 1) {
                currentIndex++
                updateGrammarView()
            } else {
                finish()
            }
        }
    }

    // 更新语法显示
    private fun updateGrammarView() {
        val grammar = grammarList[currentIndex]
        iv_grammar_image.setImageResource(grammar.imageResId)
        tv_grammar_title.text = grammar.grammarTitle
        tv_grammar_content.text = grammar.grammarContent
        tv_grammar_progress.text = "进度：${currentIndex + 1}/${grammarList.size}"
    }

    // 启动语法学习时长统计
    private fun startGrammarTimeCount() {
        grammarTimer = Timer()
        grammarTimer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                ParentModeManager.addTodayLearnTime(1)
                val todayLearnSeconds = ParentModeManager.getTodayLearnTime()
                val maxSeconds = ParentModeManager.getDailyMaxTime() * 60
                if (todayLearnSeconds >= maxSeconds) {
                    runOnUiThread {
                        Toast.makeText(this@GrammarBasicActivity, "今日学习时长已达上限", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    grammarTimer?.cancel()
                }
            }
        }, 0, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        grammarTimer?.cancel()
    }
` `}`