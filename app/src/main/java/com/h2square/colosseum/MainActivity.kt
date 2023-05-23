package com.h2square.colosseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h2square.colosseum.adapters.TopicAdapter
import com.h2square.colosseum.datas.Topic
import com.h2square.colosseum.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

class MainActivity : BaseActivity() {

    val mTopicList= ArrayList<Topic>()
    lateinit var mTopicAdapter: TopicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setupValues()
    }

    override fun setupEvents() {
        topicsListView.setOnItemClickListener { parent, view, position, id ->
                val clickedTopic = mTopicList[position]
                val myIntent=Intent(mContext,ViewTopicDetailActivity::class.java)
            myIntent.putExtra("topic",clickedTopic)
            startActivity(myIntent)
        }
    }

    override fun setupValues() {
        getTopicListFromServer()
        mTopicAdapter= TopicAdapter(mContext,R.layout.topic_list_item,mTopicList)
        topicsListView.adapter=mTopicAdapter
    }

    fun getTopicListFromServer(){
        // v2/main_info
        ServerUtil.getRequestMainTopicList(mContext,object :ServerUtil.Companion.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val dataObj =jsonObj.getJSONObject("data")
                val topicsArray = dataObj.getJSONArray("topics")

                for (index in 0 until topicsArray.length()){
                    val topicObj =topicsArray.getJSONObject(index)
                    val topicData= Topic.getTopicDataFromJson(topicObj)
                    mTopicList.add(topicData)
                }

                runOnUiThread{

                    mTopicAdapter.notifyDataSetChanged()
                }
            }

        })

    }
}