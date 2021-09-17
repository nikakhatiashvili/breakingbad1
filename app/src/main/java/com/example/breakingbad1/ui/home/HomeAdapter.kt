package com.example.breakingbad1.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbad1.databinding.CharacterItemBinding
import com.example.breakingbad1.model.Characters
import com.example.breakingbad1.network.callbackinterface.ClickListener

class HomeAdapter(private val clickListener: ClickListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var data:List<Characters> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return ViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind()
    }
    override fun getItemCount()=data.size

    inner class ViewHolder(private val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{
        private lateinit var currentData:Characters
        fun bind(){
            binding.root.setOnClickListener(this)
            currentData = data[adapterPosition]
            binding.apply {
                characterTxt.text = currentData.name
                Glide.with(itemView.context).load(currentData.img).into(binding.characterImg)
            }
        }
        override fun onClick(v: View?) {
            clickListener.onClick(currentData)
        }
    }
}