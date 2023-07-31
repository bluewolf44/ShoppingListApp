package com.example.shoppinhlistapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
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

    @POST("person")
    suspend fun addPerson(@Body person: Person)

    @GET("List/{username}/{password}")
    suspend fun getList(@Path("username") username: String,@Path("password") password: String): List<ListClass>

    @GET("List/{username}/{password}/{listId}")
    suspend fun getText(@Path("username") username: String,@Path("password") password: String,@Path("listId") listId : Int): TextClass

    @GET("Access/{username}/{password}/{listId}")
    suspend fun getAccess(@Path("username") username: String,@Path("password") password: String,@Path("listId") listId : Int): List<AccessClass>

    @DELETE("List/{username}/{password}/{listId}")
    suspend fun listDelete(@Path("username") username: String,@Path("password") password: String,@Path("listId") listId : Int)

    @POST("List/{username}/{password}")
    suspend fun addPerson(@Body list: ListCreate,@Path("username") username: String,@Path("password") password: String)

    @POST("Access/{username}/{password}/{otherUsername}/{listId}")
    suspend fun addAccess(@Path("username") username: String,@Path("password") password: String,@Path("otherUsername") otherUsername : String,@Path("listId") listId : Int)

    @PATCH("Access/{username}/{password}/{listId}")
    suspend fun changeAccess(@Body access: AccessClass, @Path("username") username: String,@Path("password") password: String,@Path("listId") listId : Int)

    @DELETE("Access/{username}/{password}/{otherUsername}/{listId}")
    suspend fun deleteAccess(@Path("username") username: String,@Path("password") password: String,@Path("otherUsername") otherUsername : String,@Path("listId") listId : Int)
}


object ShoppingAppApi {
    val retrofitService : ShoppingAppApiService by lazy {
        retrofit.create(ShoppingAppApiService::class.java)
    }

}
