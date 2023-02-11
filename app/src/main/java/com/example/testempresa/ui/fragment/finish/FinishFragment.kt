package com.example.testempresa.ui.fragment.finish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testempresa.R
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.databinding.FragmentFinishBinding
import com.example.testempresa.state.ResourceState
import com.example.testempresa.ui.fragment.detailsCars.FinishCarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishFragment : Fragment() {

    private val binding by lazy { FragmentFinishBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<FinishCarViewModel>()
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
        binding.txtNameCar.text = carTable.nameCar
        binding.txtPrice.text = carTable.price.toString()
        binding.txtYearCar.text = carTable.carYear.toString()
    }

    private fun saveCarToDB() {
        binding.btSave.setOnClickListener {
            val car = CarTable(
                id = carTable.id,
                nameFab = carTable.nameFab,
                nameCar = carTable.nameCar,
                carYear = carTable.carYear,
                carGas = carTable.carGas,
                numPort = carTable.numPort,
                price = carTable.price,
                color = carTable.color,
                name = binding.editNome.editText?.text.toString(),
                phone = binding.editPhone.editText?.text.toString(),
                email = binding.editEmail.editText?.text.toString()
            )
            viewModel.saveDataToDB(car)
        }
    }

    private fun setObserver() {
        viewModel.status.observe(viewLifecycleOwner) {
            when(it) {
                is ResourceState.Error -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                }
                is ResourceState.Success -> {
                    findNavController().navigate(R.id.action_finishFragment_to_listFragmentCars)
                }
                else -> {}
            }
        }
    }
}