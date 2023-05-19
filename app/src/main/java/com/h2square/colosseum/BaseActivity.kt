package com.h2square.colosseum

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    val mContext=this
    abstract fun setupEvents()
    abstract fun setupValues()

}