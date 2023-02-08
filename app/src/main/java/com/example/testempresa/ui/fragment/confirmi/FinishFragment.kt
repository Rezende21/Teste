package com.example.testempresa.ui.fragment.confirmi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.databinding.FragmentFinishBinding
import com.example.testempresa.state.ResourceState
import com.example.testempresa.ui.fragment.detailsCars.DetailsViewModelCars
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishFragment : Fragment() {

    private val binding by lazy { FragmentFinishBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<DetailsViewModelCars>()
    private lateinit var carTable: CarTable
    private val args : FinishFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carTable = args.cartable
        initView()
        saveCarToDB()
        setObserver()
        return binding.root
    }

    private fun initView() {
        binding.txtNameCar.text = carTable.name_car
        binding.txtPrice.text = carTable.price.toString()
        binding.txtYearCar.text = carTable.car_year.toString()
    }

    private fun saveCarToDB() {
        val car = CarTable(
            id = carTable.id,
            name_fab = carTable.name_fab,
            name_car = carTable.name_car,
            car_year = carTable.car_year,
            car_gas = carTable.car_gas,
            num_port = carTable.num_port,
            price = carTable.price,
            color = carTable.color,
            name = binding.editNome.toString(),
            phone = binding.editPhone.toString(),
            email = binding.editEmail.toString()
        )

        binding.btSave.setOnClickListener {
            viewModel.saveDataToDB(car)
        }
    }

    private fun setObserver() {
        viewModel.status.observe(viewLifecycleOwner) {
            when(it) {
                is ResourceState.Error -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }
}