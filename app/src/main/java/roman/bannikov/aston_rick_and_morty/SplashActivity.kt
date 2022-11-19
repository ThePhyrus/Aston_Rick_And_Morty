package roman.bannikov.aston_rick_and_morty

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        countDownTimer = object : CountDownTimer(SPLASH_TIME, SPLASH_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                //реализация не требуется в данном случае
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

        }.start()
    }

    companion object{
        const val SPLASH_TIME:Long = 1000L
        const val SPLASH_INTERVAL:Long = 1000L
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}