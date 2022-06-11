package com.v.nevi.p.sv.android.reaction.exercises.findnumber

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.reaction.databinding.FindNumberRecyclerItemBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel

class FindNumberAdapter(private val viewModel: ExerciseViewModel,private val list: List<Int>):RecyclerView.Adapter<FindNumberAdapter.FindNumberViewHolderViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FindNumberViewHolderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FindNumberRecyclerItemBinding.inflate(inflater,parent,false).apply {
            viewmodel = viewModel
        }
        val view = binding.root
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val side: Int = view.measuredWidth
            val lp: ViewGroup.LayoutParams = view.layoutParams
            lp.width = side
            lp.height = side
            view.layoutParams = lp
        }
        return FindNumberViewHolderViewHolder((binding))
    }

    override fun onBindViewHolder(holder: FindNumberViewHolderViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class FindNumberViewHolderViewHolder(private val binding: FindNumberRecyclerItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(number:Int){
            binding.item.text = number.toString()
        }
    }
}