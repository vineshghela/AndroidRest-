package com.qa.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface superHeroApi {

    @GET("marvel")// makes GET request to marvel end-point
    fun getHeros(): Call<List<Heros?>?>?
}