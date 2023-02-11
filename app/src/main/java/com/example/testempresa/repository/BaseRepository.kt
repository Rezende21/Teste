package com.example.testempresa.repository

import androidx.lifecycle.LiveData
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.data.remote.StatusOk
import com.example.testempresa.state.ResourceState

interface BaseRepository {

    suspend fun listCarFromApi() : ResourceState<List<Cars>>
    suspend fun sandDataToApi(car: CarTable) : ResourceState<StatusOk>

    suspend fun insertCarToBD(car: CarTable)
    suspend fun deleteCarFromDB(carId : Int)
    suspend fun showAllCars() : List<CarTable>

}