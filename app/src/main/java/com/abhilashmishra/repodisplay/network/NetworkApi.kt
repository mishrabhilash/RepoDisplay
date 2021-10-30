package com.abhilashmishra.repodisplay.network


import com.abhilashmishra.repodisplay.model.PRModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {
    @GET("repos/{user}/{repo}/pulls")
    suspend fun getAllClients(
        @Path("user") user: String,
        @Path("repo") repo: String,
        @Query("state") state: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<List<PRModel>>

    companion object {
        const val BASE_URL = "https://api.github.com/"
        operator fun invoke(): NetworkApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(NetworkApi::class.java)
        }
    }


}