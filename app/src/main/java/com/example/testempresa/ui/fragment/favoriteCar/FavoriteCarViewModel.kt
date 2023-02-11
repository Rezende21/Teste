package com.example.testempresa.ui.fragment.favoriteCar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCarViewModel @Inject constructor(
    private val repository: BaseRepository
) : ViewModel() {

    private val _listFavoriteCar = MutableLiveData<List<CarTable>>()
    val listFavoriteCar : LiveData<List<CarTable>> = _listFavoriteCar

    fun getAllCarFromDB() {
        viewModelScope.launch {
            val result = repository.showAllCars()
            _listFavoriteCar.value = result
        }
    }
}