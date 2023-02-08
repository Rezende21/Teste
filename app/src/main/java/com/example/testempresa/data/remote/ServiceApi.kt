package com.example.testempresa.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET("/cars.json")
    suspend fun listCarFromApi() : Response<List<Cars>>
}