package com.qa.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val button = findViewById<Button>(R.id.goBack)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun rawJson(view: View) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(todoApi::class.java)

        val userId = findViewById<EditText>(R.id.userId).text.toString()
        val title = findViewById<EditText>(R.id.title).text.toString()
        val completed = findViewById<Spinner>(R.id.tORf).selectedItem.toString()

        Log.d("This is the string to send?", userId + title + completed)

        val todo = Todos(Integer.parseInt(userId), 0, title, completed.toBoolean())

        // Prepare the HTTP request
        api.addTodos(todo).enqueue(object : Callback<Todos?> {
            override fun onResponse(call: Call<Todos?>, response: Response<Todos?>) {
                response.body()

                if (response.code() == 201) {
                    Toast.makeText(
                        applicationContext, "data sent" + response.isSuccessful,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext, "SHITTT" + response.isSuccessful,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Todos?>, t: Throwable) {
                Toast.makeText(
                    applicationContext, t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        Log.i("runnnn", "done")
    }
}