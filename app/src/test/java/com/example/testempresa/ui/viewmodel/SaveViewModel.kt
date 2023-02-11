package com.example.testempresa.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testempresa.MainCoroutineRule
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.repository.FakeRepository
import com.example.testempresa.state.ResourceState
import com.example.testempresa.ui.fragment.detailsCars.FinishCarViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveViewModel {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var viewModel : FinishCarViewModel

    @Before
    fun setup() {
        viewModel = FinishCarViewModel(FakeRepository())
    }

    @Test
    fun `no name pass to insertDB, return erro`() {
        val car = CarTable(1, "FORD", "Maverick", 1974, "alcool",2, 170.0, "Azul",null,"11999999999", "usuario@usuario.com")
        viewModel.saveDataToDB(car)
        val result = ResourceState.Error("Erro", null)
        assertThat(result).isNotEqualTo(ResourceState.Success(car))
    }

    @Test
    fun `pass all data to function get the result ok`() {
        val car = CarTable(1, "FORD", "Maverick", 1974, "alcool",2, 170.0, "Azul","null","11999999999", "usuario@usuario.com")
        viewModel.saveDataToDB(car)

        val result = ResourceState.Success(car)
        assertThat(result).isNotEqualTo(ResourceState.Error("Erro", null))
    }


}