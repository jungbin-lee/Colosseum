package com.h2square.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.h2square.colosseum.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.emailEdt
import kotlinx.android.synthetic.main.activity_sign_up.passwordEdt
import kotlinx.android.synthetic.main.activity_sign_up.signUpBtn
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupValues()
        setupEvents()
    }

    override fun setupEvents() {

        emailCheckBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()

            ServerUtil.getRequestDuplicate("EMAIL",inputEmail,object :ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    val code= jsonObj.getInt("code")
                    runOnUiThread { 
                        if (code == 200){
                            Toast.makeText(mContext, "사용가능합니다.", Toast.LENGTH_SHORT).show()
                        }else{
                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            })
        }

        signUpBtn.setOnClickListener {
         val inputEmail= emailEdt.text.toString()
         val inputPassword= passwordEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()
            ServerUtil.putRequestSignUp(inputEmail,inputPassword,inputNickname, object :ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    val code = jsonObj.getInt("code")
                    if(code==200){
                        val dataObj =jsonObj.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")
                        val nickname = userObj.getString("nick_name")
runOnUiThread {         Toast.makeText(mContext, "${nickname}님 환영합니다.", Toast.LENGTH_SHORT).show()
finish()}


                    }else{
                        val message= jsonObj.getString("message")
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }

    }

    override fun setupValues() {

    }
}