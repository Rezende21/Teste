package com.example.testempresa.ui.fragment.detailsCars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.databinding.FragmentDetailsCarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragmentCar : Fragment() {

    private val binding by lazy { FragmentDetailsCarBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<DetailsViewModelCars>()
    private lateinit var cartable : CarTable
    private val args : DetailsFragmentCarArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartable = args.car
        initView()
        completeRegister()
        return binding.root
    }

    private fun initView() {
        binding.txtCarFabResult.text = cartable.name_fab
        binding.txtCarNameResult.text = cartable.name_car
        binding.txtCarYearResult.text = cartable.car_year.toString()
        binding.txtCarGasResult.text = cartable.car_gas
        binding.txtCarPortResult.text = cartable.num_port.toString()
        binding.txtCarValorResult.text = cartable.price.toString()
        binding.txtCarColorResult.text = cartable.color
    }

    private fun completeRegister() {
        binding.btGetIt.setOnClickListener {
            val action = DetailsFragmentCarDirections.actionDetailsFragmentCarToFinishFragment(cartable)
            findNavController().navigate(action)
        }
    }


}