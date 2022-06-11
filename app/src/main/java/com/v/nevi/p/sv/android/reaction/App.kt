package com.v.nevi.p.sv.android.reaction

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.v.nevi.p.sv.android.reaction.exercises.ExercisesRepository

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        ExercisesRepository.initialize(this)
    }

}