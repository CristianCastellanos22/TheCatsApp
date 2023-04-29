package com.cristian.castellanos.thecatsapp.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cristian.castellanos.thecatsapp.R
import com.cristian.castellanos.thecatsapp.databinding.CatListItemBinding
import com.cristian.castellanos.thecatsapp.models.Cat

class CatAdapter : ListAdapter<Cat, CatAdapter.CatListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListViewHolder {
        val view = CatListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatListViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)
    }

    inner class CatListViewHolder(private val binding: CatListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cat: Cat) {
            with(binding) {
                idNameCat.text = cat.breedName
                countryCat.text = "Origin:  ${cat.origin}"
                imgCat.load(cat.imageUrl) {
                    placeholder(R.drawable.baseline_error_outline)
                        .error(R.drawable.cat_logo)
                }
                intelligenceCat.text = "Intelligence: ${cat.intelligence}"
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.breedName == newItem.breedName
        }

    }

}