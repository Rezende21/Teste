package com.example.testempresa.repository

import com.example.testempresa.data.local.CarTable
import com.example.testempresa.data.local.CarDao
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.data.remote.ServiceApi
import com.example.testempresa.data.remote.StatusOk
import com.example.testempresa.state.ResourceState
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

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

    override suspend fun sandDataToApi(car: CarTable): ResourceState<StatusOk> {
        val body = Gson().toJson(car).toRequestBody("application/json; charset=UTF-8".toMediaType())
        val stats = api.sandDataToApi(body)
        if (stats.isSuccessful) {
            insertCarToBD(car)
            stats.body()?.let {
                return ResourceState.Success(it)
            }
        }
        return ResourceState.Error("Erro ao receber dados", null)
    }

    override suspend fun insertCarToBD(car: CarTable) {
        dao.insertCarToDB(car)

    }

    override suspend fun deleteCarFromDB(carId : Int) {
        dao.deleteCarFromDB(carId)
    }

    override suspend fun showAllCars(): List<CarTable> {
        return dao.showAllCars()
    }
}