package com.example.testempresa.data.remote

import com.example.testempresa.data.local.CarTable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServiceApi {

    @GET("/cars.json")
    suspend fun listCarFromApi() : Response<List<Cars>>

    @POST("/cars/leads")
    suspend fun sandDataToApi(
        @Body requestBody: RequestBody
    ) : Response<StatusOk>
}