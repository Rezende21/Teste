package com.example.testempresa.ui.fragment.detailsCars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.repository.BaseRepository
import com.example.testempresa.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModelCars @Inject constructor(
    private val repository: BaseRepository
) : ViewModel() {

    private val _status = MutableLiveData<ResourceState<String>>()
    val status : LiveData<ResourceState<String>> = _status

    fun saveDataToDB(car: CarTable) {
        if (car.name.isNullOrBlank() || car.email.isNullOrBlank() || car.phone.isNullOrBlank()) {
            _status.value = ResourceState.Error("Por Favor Preencher todos os campos", null)
        } else {
            viewModelScope.launch {
                repository.insertCarToBD(car)
            }
        }
    }

}