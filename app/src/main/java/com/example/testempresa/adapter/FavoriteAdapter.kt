package com.example.testempresa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testempresa.data.local.CarTable
import com.example.testempresa.databinding.ItemListBinding

class FavoriteAdapter(
    private val onClick : (CarTable) -> Unit
) : ListAdapter<CarTable, FavoriteAdapter.MyFavoriteViewHolder>(DiffCallback()) {

    class MyFavoriteViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : CarTable) {
            binding.txtNameCar.text = item.nameCar
            binding.txtYearCar.text = item.carYear.toString()
            binding.txtPrice.text = item.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return MyFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyFavoriteViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(currentItem)
        }
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<CarTable>() {
        override fun areItemsTheSame(oldItem: CarTable, newItem: CarTable): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CarTable, newItem: CarTable): Boolean = oldItem == newItem
    }
}