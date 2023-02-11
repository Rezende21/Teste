package com.example.testempresa.ui.fragment.listCars

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.work.WorkManager
import com.example.testempresa.R
import com.example.testempresa.adapter.CarAdapter
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.databinding.FragmentListCarsBinding
import com.example.testempresa.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCarsFragment : Fragment(), MenuProvider {

    private val binding by lazy { FragmentListCarsBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<ListCarsViewModel>()
    private val adapter by lazy { CarAdapter {
        getDetailsCar(it)
    }}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, lifecycle.currentState)
        getCarFromApi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
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
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
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
            nameFab = car.marcaNome,
            nameCar = car.nomeModelo,
            carYear = car.ano,
            carGas = car.combustivel,
            numPort = car.numPortas,
            price = car.valorFipe,
            color = car.cor
        )
        val action = ListCarsFragmentDirections.actionListFragmentCarsToDetailsFragmentCar(cartable)
        findNavController().navigate(action)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.favorite -> {
                findNavController().navigate(R.id.action_listFragmentCars_to_favoriteCarFragment)
                true
            }
            else -> { true }
        }
    }
}