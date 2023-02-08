package com.example.testempresa.repository

import androidx.lifecycle.LiveData
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.state.ResourceState

interface BaseRepository {

    suspend fun listCarFromApi() : ResourceState<List<Cars>>

    suspend fun insertCarToBD(car: CarTable)
    suspend fun deleteCarFromDB(carId : Int)
    fun showAllCars() : LiveData<List<CarTable>>

}