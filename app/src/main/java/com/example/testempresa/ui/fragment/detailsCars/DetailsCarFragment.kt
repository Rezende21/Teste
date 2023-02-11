package com.example.testempresa.ui.fragment.detailsCars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.databinding.FragmentDetailsCarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsCarFragment : Fragment() {

    private val binding by lazy { FragmentDetailsCarBinding.inflate(layoutInflater) }
    private lateinit var cartable : CarTable
    private val args : DetailsCarFragmentArgs by navArgs()

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
        binding.txtCarFabResult.text = cartable.nameFab
        binding.txtCarNameResult.text = cartable.nameCar
        binding.txtCarYearResult.text = cartable.carYear.toString()
        binding.txtCarGasResult.text = cartable.carGas
        binding.txtCarPortResult.text = cartable.numPort.toString()
        binding.txtCarValorResult.text = cartable.price.toString()
        binding.txtCarColorResult.text = cartable.color
    }

    private fun completeRegister() {
        binding.btGetIt.setOnClickListener {
            val action = DetailsCarFragmentDirections.actionDetailsFragmentCarToFinishFragment(cartable)
            findNavController().navigate(action)
        }
    }


}