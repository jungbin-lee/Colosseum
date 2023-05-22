package com.h2square.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.h2square.colosseum.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setupValues()
    }

    override fun setupEvents() {
        loginBtn.setOnClickListener{
            val inputEmail =emailEdt.text.toString()
            val inputPassword =passwordEdt.text.toString()


            //(APIRequest)
    ServerUtil.postRequestLogin(inputEmail,inputPassword, object :ServerUtil.Companion.JsonResponseHandler{
        override fun onResponse(jsonObj: JSONObject) {
            val code =jsonObj.getInt("code")
            if(code==200){
                //로그인 성공

            }else{
                //로그인 실패
                val message = jsonObj.getString("message")
                runOnUiThread{
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                }

            }
        }

    })



        }

    }

    override fun setupValues() {

    }
}