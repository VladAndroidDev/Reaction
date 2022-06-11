package com.v.nevi.p.sv.android.reaction.exercises.math

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.reaction.databinding.MathRecyclerItemBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel

class MathAdapter(private val viewModel: ExerciseViewModel, private val list: List<String>):RecyclerView.Adapter<MathAdapter.MathViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MathViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MathRecyclerItemBinding.inflate(inflater,parent,false).apply {
            viewmodel = viewModel
        }
        return MathViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MathViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class MathViewHolder(private val binding:MathRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(operation:String){
            binding.item.text = operation
        }

    }

}