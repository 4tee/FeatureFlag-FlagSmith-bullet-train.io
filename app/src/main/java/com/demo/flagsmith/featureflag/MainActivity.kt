package com.demo.flagsmith.featureflag

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch{
            // runs on UI thread
            makeFeatureFlagsNetworkCall()
        }
    }

    private suspend fun makeFeatureFlagsNetworkCall() {
        withContext(Dispatchers.Default) {
            val featureEnabled = (application as MyApplication).flagsmith.hasFeatureFlag("button_visibility")
            if (featureEnabled) {
                button.visibility = View.VISIBLE
            } else {
                // Run the code if feature switched off
                button.visibility = View.GONE
            }

            val myRemoteConfigForFruit = (application as MyApplication).flagsmith.getFeatureFlagValue("fruit")
            val myRemoteConfigForFruitTextSize = (application as MyApplication).flagsmith.getFeatureFlagValue("text_size")
            textView_fruit.text = myRemoteConfigForFruit
            textView_fruit.textSize = myRemoteConfigForFruitTextSize.toFloat()
        }
    }
}