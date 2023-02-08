package com.example.testempresa.repository

import androidx.lifecycle.LiveData
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.data.local.CarDao
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.data.remote.ServiceApi
import com.example.testempresa.state.ResourceState

class RepositoryImp(
    private val api : ServiceApi,
    private val dao: CarDao
    ) : BaseRepository {


    override suspend fun listCarFromApi() : ResourceState<List<Cars>> {
        val responce = api.listCarFromApi()
        if (responce.isSuccessful) {
            responce.body()?.let {
                return ResourceState.Success(it)
            }
        }
        return ResourceState.Error("Erro ao carregar os dados", null)
    }

    override suspend fun insertCarToBD(car: CarTable) {
        dao.insertCarToDB(car)
    }

    override suspend fun deleteCarFromDB(carId : Int) {
        dao.deleteCarFromDB(carId)
    }

    override fun showAllCars(): LiveData<List<CarTable>> {
        return dao.showAllCars()
    }
}