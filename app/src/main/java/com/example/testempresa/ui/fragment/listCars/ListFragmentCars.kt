package com.example.testempresa.ui.fragment.listCars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testempresa.R
import com.example.testempresa.adapter.CarAdapter
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.databinding.FragmentListCarsBinding
import com.example.testempresa.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragmentCars : Fragment() {

    private val binding by lazy { FragmentListCarsBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<ListViewModelCars>()
    private val adapter by lazy { CarAdapter {
        getDetailsCar(it)
    }}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getCarFromApi()
        return binding.root
    }

    private fun getCarFromApi() {
        binding.recycleView.adapter = adapter
        viewmodel.listCarsFromApi()
        viewmodel.listCars.observe(viewLifecycleOwner){
            when(it) {
                is ResourceState.Success -> {
                    adapter.submitList(it.data)
                }
                is ResourceState.Error -> {
                    //TODO Colocar um sistema de erro melhor com diolog
                }
                is ResourceState.Loading -> {
                    //TODO Exibir um progresbar
                }
            }
        }
    }

    private fun getDetailsCar(car : Cars) {
        val cartable = CarTable(
            id = car.id,
            name_fab = car.marca_nome,
            name_car = car.nome_modelo,
            car_year = car.ano,
            car_gas = car.combustivel,
            num_port = car.num_portas,
            price = car.valor_fipe,
            color = car.cor
        )
        val action = ListFragmentCarsDirections.actionListFragmentCarsToDetailsFragmentCar(cartable)
        findNavController().navigate(action)
    }
}