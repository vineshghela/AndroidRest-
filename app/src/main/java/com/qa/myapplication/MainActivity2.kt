package com.qa.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val button =findViewById<Button>(R.id.goBack)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity2,MainActivity::class.java)
            startActivity(intent)

//        val sendD = findViewById<Button>(R.id.sendData)
//            sendD.setOnClickListener {
//                rawJ
//            }

            }


    }

    fun rawJson(view: View){

        Log.i("Hello", "THIS IS MEEE")
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val api= retrofit.create(todoApi::class.java)

                var todo = Todos(1,0,"Hey Hey",true)


        // Prepare the HTTP request
        api.addTodos(todo).enqueue(object:Callback<Todos?>{
            override fun onResponse(call: Call<Todos?>, response: Response<Todos?>) {
                val todo = response.body()

                if (response.code() == 201){
                    Toast.makeText(applicationContext,"data sent"+response.isSuccessful,
                        Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext,"SHITTT"+response.isSuccessful,
                        Toast.LENGTH_LONG).show()
                }


            }

            override fun onFailure(call: Call<Todos?>, t: Throwable) {
                Toast.makeText(applicationContext, t.message,
                    Toast.LENGTH_SHORT).show()
            }
        })

                Log.i("runnnn", "done")

//                var jsonObject = JSONObject()
//                jsonObject.put("userId", 1)
//                jsonObject.put("title", "Testing")
//                jsonObject.put("completed", true)
//
//                var jsonObjectString = jsonObject.toString()
//
//
//                api.addTodos(todo).enqueue(object : Callback<Todos>){
//                    override fun onResponse(call:)
//                }



    }
}