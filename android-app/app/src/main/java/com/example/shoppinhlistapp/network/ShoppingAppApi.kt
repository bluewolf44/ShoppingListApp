package com.example.shoppinhlistapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:8080"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


//interface MarsApiService {
//    @GET("photos")
//    suspend fun getPhotos(): List<MarsPhoto>
//}

interface ShoppingAppApiService {
    @GET("person/{username}/{password}")
    suspend fun getPerson(@Path("username") username: String,@Path("password") password: String): Person


    //@POST("person")
    //fun postRequest(@Body post: Customer): Call<Customer>
}


object ShoppingAppApi {
    val retrofitService : ShoppingAppApiService by lazy {
        retrofit.create(ShoppingAppApiService::class.java)
    }

}
