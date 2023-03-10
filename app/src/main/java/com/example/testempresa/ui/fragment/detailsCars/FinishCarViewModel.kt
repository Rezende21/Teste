package com.example.testempresa.ui.fragment.detailsCars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.repository.BaseRepository
import com.example.testempresa.data.remote.StatusOk
import com.example.testempresa.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishCarViewModel @Inject constructor(
    private val repository: BaseRepository
) : ViewModel() {

    private val _status = MutableLiveData<ResourceState<StatusOk>>()
    val status : LiveData<ResourceState<StatusOk>> = _status

    fun saveDataToDB(car: CarTable) {
        if (car.name.isNullOrBlank() || car.email.isNullOrBlank() || car.phone.isNullOrBlank()) {
            _status.value = ResourceState.Error("Por Favor Preencher todos os campos", null)
        } else {
            viewModelScope.launch {
                val status = repository.sandDataToApi(car)
                _status.postValue(status)
            }
        }
    }

}