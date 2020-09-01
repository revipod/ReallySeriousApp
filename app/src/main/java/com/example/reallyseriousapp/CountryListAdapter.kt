package com.example.reallyseriousapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.reallyseriousapp.databinding.RowPropertyViewBinding
import javax.inject.Inject

class CountryListAdapter @Inject constructor() : RecyclerView.Adapter<CountryItemViewHolder>() {

    private val itemViewModelList : MutableList<CountryItemViewModel> = ArrayList()

    fun setAdapterData(countryList: MutableList<CountryItemViewModel>) {
        itemViewModelList.clear()
        itemViewModelList.addAll(countryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = RowPropertyViewBinding.inflate(layoutInflater, parent, false)
        return CountryItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return itemViewModelList.size
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        val itemViewModel = itemViewModelList[position]
        holder.binding.itemViewModel = itemViewModel
    }
}

class CountryItemViewHolder(val binding: RowPropertyViewBinding) : RecyclerView.ViewHolder(binding.root)


