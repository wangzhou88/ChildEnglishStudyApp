import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.child.wordstudy.R
import com.child.wordstudy.utils.ScoreManager

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // 显示当前总积分
        updateScoreDisplay()

        // 积分兑换按钮
        findViewById<Button>(R.id.btn_score_exchange).setOnClickListener {
            if (ScoreManager.canExchangeReward()) {
                if (ScoreManager.exchangeReward()) {
                    updateScoreDisplay()
                    android.widget.Toast.makeText(this, "兑换成功！", android.widget.Toast.LENGTH_SHORT).show()
                }
            } else {
                android.widget.Toast.makeText(this, "积分不足，需要10积分", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        // 返回主页按钮
        findViewById<Button>(R.id.btn_back_to_main).setOnClickListener {
            finish()
        }
    }

    private fun updateScoreDisplay() {
        val totalScore = ScoreManager.getTotalScore()
        val dailyScore = ScoreManager.getDailyScore()
        
        findViewById<TextView>(R.id.tv_my_total_score).text = "总积分：$totalScore"
        findViewById<TextView>(R.id.tv_my_daily_score).text = "今日积分：$dailyScore"
    }
` `}`