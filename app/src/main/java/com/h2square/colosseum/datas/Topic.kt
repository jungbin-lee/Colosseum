package com.h2square.colosseum.datas

import org.json.JSONObject
import java.io.Serializable

class Topic: Serializable {

    var id =0
    var title = ""
    var imgUrl =""

    companion object{
        fun getTopicDataFromJson(jsonObj: JSONObject):Topic{
            val resultTopic= Topic()
            resultTopic.id=jsonObj.getInt("id")
            resultTopic.title=jsonObj.getString("title")
            resultTopic.imgUrl=jsonObj.getString("img_url")
            return resultTopic
        }
    }



}