package com.v.nevi.p.sv.android.reaction.exercises.catchcolor

import android.graphics.drawable.RippleDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.CatchColorRecyclerItemBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.utils.overrideColor

class CatchColorAdapter(
    private val viewModel: ExerciseViewModel,
    private val listColors: List<Int>
) : RecyclerView.Adapter<CatchColorAdapter.CatchColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatchColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatchColorRecyclerItemBinding.inflate(inflater, parent, false).apply {
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
        return CatchColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatchColorViewHolder, position: Int) {
        holder.bind(listColors[position])
    }

    override fun getItemCount() = listColors.size

    class CatchColorViewHolder(private val binding: CatchColorRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(colorId: Int) {
            binding.item.tag = colorId
            val rippleDrawable = binding.item.background as RippleDrawable
            val drawable = rippleDrawable.findDrawableByLayerId(R.id.shape_catch_color_item_view)
            drawable.overrideColor(binding.root.context, colorId)
        }

    }
}