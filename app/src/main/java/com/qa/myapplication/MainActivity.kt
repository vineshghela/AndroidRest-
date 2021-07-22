package com.qa.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.ListView



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button =findViewById<Button>(R.id.addPost)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java)
            startActivity(intent)
        }


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(todoApi::class.java)

        api.getTodos()?.enqueue(object : Callback<List<Todos?>?> {
            override fun onResponse(call: Call<List<Todos?>?>?,
                                    response: Response<List<Todos?>?>) {
                val heroList: List<Todos?>? = response.body() // Now we can use
                //this heroList for getting the data from API.

                //Creating an String array for the ListView
                val heroes = arrayOfNulls<String>(heroList?.size!!)


                //looping through all the heroes and inserting the names
                //  inside the string array
                for (i in heroList.indices) {
                    heroes[i] =heroList[i]?.id.toString() +" : "+ heroList[i]?.title +", "+ heroList[i]?.completed
//                    Log.i("heroossss",""+heroes[i])
                }

                //displaying the string array into listview
                val listview =findViewById<ListView>(R.id.listView)
                listview.adapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_list_item_1,
                    heroes,
                ) //Part Extra
            }

            override fun onFailure(call: Call<List<Todos?>?>?, t: Throwable) {
                Toast.makeText(applicationContext, t.message,
                    Toast.LENGTH_SHORT).show()
            }
        }) //Part 3




    }
}