package com.example.testempresa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testempresa.data.remote.Cars
import com.example.testempresa.databinding.ItemListBinding

class CarAdapter(
    private val onClick : (Cars) -> Unit
) : ListAdapter<Cars, CarAdapter.MyViewHolder>(DiffCallback()) {

    class MyViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Cars) {
            binding.txtNameCar.text = item.nomeModelo
            binding.txtYearCar.text = item.ano.toString()
            binding.txtPrice.text = item.valorFipe.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(currentItem)
        }
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Cars>() {
        override fun areItemsTheSame(oldItem: Cars, newItem: Cars): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Cars, newItem: Cars): Boolean = oldItem == newItem
    }
}