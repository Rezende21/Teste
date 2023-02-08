package com.example.testempresa.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.state.ResourceState

class FakeRepository : BaseRepository {

    private val car = mutableListOf<CarTable>()
    private val showAllCars = MutableLiveData<List<CarTable>>()
    private var netWorkError = false

    private fun refreshLiveData() {
        showAllCars.postValue(car)
    }

    override suspend fun listCarFromApi(): ResourceState<List<Cars>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCarToBD(cartable: CarTable) {
        car.add(cartable)
        refreshLiveData()
    }

    override suspend fun deleteCarFromDB(carId: Int) {
        car.removeAt(carId)
        refreshLiveData()
    }

    override fun showAllCars(): LiveData<List<CarTable>> {
        return showAllCars
    }
}