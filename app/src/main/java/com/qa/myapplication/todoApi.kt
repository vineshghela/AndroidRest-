package com.qa.myapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST

interface todoApi {

    @GET("todos")
    fun getTodos(): Call<List<Todos?>?>?


    @POST("todos")
    fun addTodos(@Body todos: Todos):Call<Todos>

}