package com.v.nevi.p.sv.android.reaction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adRequestAdView = AdRequest.Builder().build()
        findViewById<AdView>(R.id.adView).loadAd(adRequestAdView)
    }
}