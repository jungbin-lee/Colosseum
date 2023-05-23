package com.h2square.colosseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.h2square.colosseum.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setupValues() {
            val myHandler = Handler(Looper.getMainLooper())
            myHandler.postDelayed({
                                  if (ContextUtil.getToken(mContext)==""){
                                      val myIntent= Intent(mContext,LoginActivity::class.java)
                                      startActivity(myIntent)
                                  }else{
                                      val myIntent= Intent(mContext,MainActivity::class.java)
                                      startActivity(myIntent)
                                  }
                finish()

            },2000)
    }
}
