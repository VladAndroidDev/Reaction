package com.v.nevi.p.sv.android.reaction.exercises.findcolor

import android.graphics.drawable.RippleDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.FindColorRecyclerItemBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.utils.overrideColor


class FindColorAdapter(private val viewModel: ExerciseViewModel, private val list: List<Color>):RecyclerView.Adapter<FindColorAdapter.FindColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FindColorRecyclerItemBinding.inflate(inflater,parent,false).apply {
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
        return FindColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FindColorViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class FindColorViewHolder(private val binding:FindColorRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(color: Color){
            val rippleDrawable = binding.item.background as RippleDrawable
            val drawable = rippleDrawable.findDrawableByLayerId(R.id.shape_find_color_item_view)
            drawable.overrideColor(binding.item.context,color.colorId)
            binding.item.tag = color.nameId
        }

    }
}