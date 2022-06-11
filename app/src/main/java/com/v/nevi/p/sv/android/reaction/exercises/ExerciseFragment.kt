package com.v.nevi.p.sv.android.reaction.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.gms.ads.AdRequest
import com.v.nevi.p.sv.android.reaction.BaseFragment
import com.v.nevi.p.sv.android.reaction.EventObserver
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.ReactionPreferencesManager
import com.v.nevi.p.sv.android.reaction.databinding.FragmentExerciseBinding
import com.v.nevi.p.sv.android.reaction.utils.ViewModelFactory
import com.v.nevi.p.sv.android.reaction.utils.getResult

class ExerciseFragment : BaseFragment(){

    override val viewModel:ExerciseViewModel by viewModels {
        ViewModelFactory(requireArguments().getString("id"))
    }
    private lateinit var binding: FragmentExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentExerciseBinding>(inflater, R.layout.fragment_exercise,container,false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.bindData(inflater,binding.container,viewLifecycleOwner)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getResult<Boolean>(ARG_RESULT){
            viewModel.restart()
        }
        setupCloseNavigation()
        setObservers()
    }

    private fun setObservers() {
        viewModel.onFinishEvent.observe(viewLifecycleOwner, EventObserver{
            try {
                val action = ExerciseFragmentDirections.toResult(it)
                findNavController().navigate(action, navOptions {
                    anim {
                        enter = R.anim.enter
                        exit = R.anim.exit
                        popEnter = R.anim.pop_enter
                        popExit = R.anim.pop_exit
                    }
                })
            }catch (e:Exception){

            }
        })
    }
    companion object{

        const val ARG_RESULT = "arg-result"

    }
}