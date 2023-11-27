package com.anne.play

import android.app.Application
import com.anne.play.logic.utils.DataStoreUtils

/**
 *
 * Author:AnneLo
 * Time:2023/11/27
 */
class App : Application() {
    private var dataStore = DataStoreUtils
    override fun onCreate() {
        super.onCreate()
        DataStoreUtils.init(applicationContext)
        Play.initialize(dataStore)
    }
}
