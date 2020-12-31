package com.demo.flagsmith.featureflag

import android.app.Application
import com.solidstategroup.bullettrain.BulletTrainClient

class MyApplication : Application() {

    lateinit var flagsmith: BulletTrainClient

    override fun onCreate() {
        super.onCreate()
         flagsmith = BulletTrainClient.newBuilder()
            .setApiKey("JsQUenK5Rri3AgzoxfBvzb")
            .build()
    }
}