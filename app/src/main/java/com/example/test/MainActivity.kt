package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val data = ArrayList<ItemsViewModel>()

    //OkHttpClient creates connection pool between client and server
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        run("https://api.themoviedb.org/3/search/movie?api_key=38e61227f85671163c275f9bd95a8803&query=marvel")

    }



    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                var strResponse = response.body!!.string()
                //creating json object
                val jsonContact: JSONObject = JSONObject(strResponse)
                //creating json array
                var jsonarray_info: JSONArray = jsonContact.getJSONArray("results")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject = jsonarray_info.getJSONObject(i)
                    var model:ItemsViewModel= ItemsViewModel();
                    model.title = json_objectdetail.getString("title")
                    model.description = json_objectdetail.getString("overview")
                    model.image = json_objectdetail.getString("poster_path")
                    data.add(model)
                }

                runOnUiThread {
                    val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

                    val objAdapter : CustomAdapter = CustomAdapter(data)
                    recyclerview.adapter = objAdapter

                }
            }
        })
    }
}


