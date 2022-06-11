package com.v.nevi.p.sv.android.reaction.menu.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.ReactionPreferencesManager
import com.v.nevi.p.sv.android.reaction.exercises.Exercise
import com.v.nevi.p.sv.android.reaction.exercises.ExercisesRepository
import com.v.nevi.p.sv.android.reaction.menu.MenuFragment
import com.v.nevi.p.sv.android.reaction.utils.setResult

class MenuDialogFragment : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { frActivity ->
            val builder = MaterialAlertDialogBuilder(frActivity, R.style.AlertDialogThemeExit)
            builder.setTitle(R.string.blocked)
            .setMessage(R.string.blocked_message)
                .setPositiveButton(R.string.open_with_ads) { dialog, _ ->
                    setResult(MenuFragment.ARG_ADD_SHOWED,requireArguments().getString("id"))
                    dialog.cancel()
                }.setNegativeButton(R.string.ok) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalAccessException()
    }



}