package com.example.testempresa.ui.fragment.favoriteCar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testempresa.adapter.FavoriteAdapter
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.databinding.FragmentFavoriteCarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteCarFragment : Fragment() {

    private val binding by lazy { FragmentFavoriteCarBinding.inflate(layoutInflater)}
    private val viewModel by viewModels<FavoriteCarViewModel>()
    private val adapter by lazy { FavoriteAdapter{
        getDetailsCar(it)
    }}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpAdapter()
        return binding.root
    }

    private fun setUpAdapter() {
        binding.recycleView.adapter = adapter
        viewModel.getAllCarFromDB()
        viewModel.listFavoriteCar.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun getDetailsCar(car: CarTable) {
        val action = FavoriteCarFragmentDirections.actionFavoriteCarFragmentToDetailsFragmentCar(car)
        findNavController().navigate(action)
    }

}