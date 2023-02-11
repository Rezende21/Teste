package com.example.testempresa.ui.fragment.listCars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.repository.BaseRepository
import com.example.testempresa.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCarsViewModel @Inject constructor(
    private val repository: BaseRepository
) : ViewModel() {

    private val _listCars = MutableLiveData<ResourceState<List<Cars>>>()
    val listCars : LiveData<ResourceState<List<Cars>>> = _listCars

    fun listCarsFromApi() {
        _listCars.value = ResourceState.Loading()
        viewModelScope.launch {
            val result = repository.listCarFromApi()
            _listCars.postValue(result)
        }
    }

}