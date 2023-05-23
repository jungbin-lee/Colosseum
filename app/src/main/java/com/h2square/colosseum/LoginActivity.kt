package com.h2square.colosseum

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.h2square.colosseum.utils.ContextUtil
import com.h2square.colosseum.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailEdt
import kotlinx.android.synthetic.main.activity_login.passwordEdt
import kotlinx.android.synthetic.main.activity_login.signUpBtn
import org.json.JSONObject

class LoginActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setupValues()
    }

    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            val myIntent= Intent(mContext,SignUpActivity::class.java)
            startActivity(myIntent)

        }

        loginBtn.setOnClickListener{
            val inputEmail =emailEdt.text.toString()
            val inputPassword =passwordEdt.text.toString()


            //(APIRequest)
    ServerUtil.postRequestLogin(inputEmail,inputPassword, object :ServerUtil.Companion.JsonResponseHandler{
        override fun onResponse(jsonObj: JSONObject) {
            val code =jsonObj.getInt("code")
            if(code==200){
                //로그인 성공
                val dataObj= jsonObj.getJSONObject("data")
                val token = dataObj.getString("token")
                ContextUtil.setToken(mContext,token)

                val loginIntent = Intent(mContext, MainActivity::class.java)
                startActivity(loginIntent)
                finish()

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