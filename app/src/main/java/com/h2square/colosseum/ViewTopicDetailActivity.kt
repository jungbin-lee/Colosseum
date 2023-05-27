package com.h2square.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.h2square.colosseum.datas.Topic
import com.h2square.colosseum.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*

class ViewTopicDetailActivity : BaseActivity(){
    lateinit var mTpoic : Topic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)
        setupValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setupValues() {
        mTpoic =intent.getSerializableExtra("topic")as Topic

        topicTitleTxt.text=mTpoic.title
        Glide.with(mContext).load(mTpoic.imgUrl).into(topicImg)
        getTopicDetailFromServer()

    }
    fun getTopicDetailFromServer(){


    }
}