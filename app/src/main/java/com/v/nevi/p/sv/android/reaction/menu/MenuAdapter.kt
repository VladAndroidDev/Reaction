package com.v.nevi.p.sv.android.reaction.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.reaction.databinding.MenuRecylerItemBinding
import com.v.nevi.p.sv.android.reaction.exercises.Exercise

class MenuAdapter(private val viewModel: MenuViewModel) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

   var list:List<Exercise> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun notifyExerciseChanged(exercise: Exercise){
        val position = list.indexOf(exercise)
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MenuRecylerItemBinding.inflate(inflater, parent, false).apply {
            viewmodel = viewModel
        }
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(list[position], position + 1)
    }

    override fun getItemCount() = list.size

    class MenuViewHolder(private val binding: MenuRecylerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise, position: Int) {
            binding.exercise = exercise
            binding.position = position
        }
    }

}