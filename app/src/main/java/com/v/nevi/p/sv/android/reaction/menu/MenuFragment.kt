package com.v.nevi.p.sv.android.reaction.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.v.nevi.p.sv.android.reaction.EventObserver
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.ReactionPreferencesManager
import com.v.nevi.p.sv.android.reaction.databinding.FragmentMenuBinding
import com.v.nevi.p.sv.android.reaction.exercises.Exercise
import com.v.nevi.p.sv.android.reaction.exercises.ExercisesRepository
import com.v.nevi.p.sv.android.reaction.utils.getResult

class MenuFragment : Fragment() {

    private lateinit var binding:FragmentMenuBinding
    private val viewModel:MenuViewModel by viewModels()
    private var mRewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(ExercisesRepository.hasNotOpenedExercises()){
            loadAdd()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater,container,false).apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        binding.menuRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MenuAdapter(viewModel).apply {
                list = ExercisesRepository.listExercises
            }
            setHasFixedSize(true)
            setItemViewCacheSize(ExercisesRepository.listExercises.size)
        }
        getResult<String>(ARG_ADD_SHOWED){
            showAdd(it)
        }
    }

    private fun setObservers(){
        viewModel.onItemClickEvent.observe(viewLifecycleOwner,EventObserver{
            navigateToExercise(it)
        })
        viewModel.onSettingsClickEvent.observe(viewLifecycleOwner,EventObserver{
            navigateToSettings()
        })
        viewModel.startDialogEvent.observe(viewLifecycleOwner,EventObserver{
            startDialog(it)
        })
    }

    private fun startDialog(id: String){
        try {
            val action = MenuFragmentDirections.actionMenuDestToMenuDialogFragment(id)
            findNavController().navigate(action)
        }catch (e:Exception){

        }
    }

    private fun navigateToSettings(){
        val action = MenuFragmentDirections.actionMenuDestToSettingsFragment()
        findNavController().navigate(action)
    }

    private fun navigateToExercise(id:String){
        try {
            val action = MenuFragmentDirections.fromMenuToExercise(id)
            findNavController().navigate(action,navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            })
        }catch (e:Exception){
            
        }

    }

    private fun loadAdd(){
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(requireContext(),"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mRewardedAd = null
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                mRewardedAd = rewardedAd
            }
        })
    }

    private fun showAdd(id:String){
        if (mRewardedAd != null) {
            mRewardedAd?.show(requireActivity()) {
                val exercise = ExercisesRepository.getExerciseById(id)
                exercise.isOpened = true
                ReactionPreferencesManager.saveExercise(requireContext(),exercise)
                val adapter = binding.menuRecycler.adapter as MenuAdapter
                adapter.notifyExerciseChanged(exercise)
                mRewardedAd = null
                loadAdd()
            }
        } else {
            Toast.makeText(requireContext(),R.string.no_loaded,Toast.LENGTH_LONG).show()
            loadAdd()
        }
    }

    companion object{

        const val ARG_ADD_SHOWED = "show-add"

    }
}