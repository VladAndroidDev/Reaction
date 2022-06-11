package com.v.nevi.p.sv.android.reaction.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.v.nevi.p.sv.android.reaction.BaseFragment
import com.v.nevi.p.sv.android.reaction.EventObserver
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.FragmentResultBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseFragment
import com.v.nevi.p.sv.android.reaction.utils.ViewModelFactory
import com.v.nevi.p.sv.android.reaction.utils.setResult


class ResultFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            navigateToMenu()
        }
    }

    override val viewModel: ResultViewModel by viewModels {
        ViewModelFactory(result = requireArguments().getParcelable("res")!!)
    }
    private lateinit var binding:FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater,container,false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCloseNavigation()
        setObservers()
    }

    override fun onCloseClick() {
        navigateToMenu()
    }

    private fun setObservers(){
        viewModel.onRepeatClickEvent.observe(viewLifecycleOwner,EventObserver{
            setResult(ExerciseFragment.ARG_RESULT,true)
            findNavController().popBackStack()
        })
        viewModel.onMenuClickEvent.observe(viewLifecycleOwner,EventObserver{
            navigateToMenu()
        })
    }

    private fun navigateToMenu(){
        findNavController().popBackStack(R.id.menu_dest,false)
    }
}