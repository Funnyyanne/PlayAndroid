package com.anne.play

import com.anne.play.logic.utils.DataStoreUtils

/**
 * 全局API接口
 * Author:AnneLo
 * Time:2023/10/2
 */

object Play {

    private const val USERNAME = "username"
    private const val NICK_NAME = "nickname"
    private const val IS_LOGIN = "isLogin"
    private var dataStore = DataStoreUtils

    fun initialize(dataStoreUtils: DataStoreUtils) {
        dataStore = dataStoreUtils
    }

    fun setUserInfo(nickname: String, username: String) {
        dataStore.saveSyncStringData(NICK_NAME, nickname)
        dataStore.saveSyncStringData(USERNAME, username)
    }

    val nickname: String
        get() = dataStore.readStringData(NICK_NAME)
    val username: String
        get() = dataStore.readStringData(NICK_NAME)

    fun logout() {
        dataStore.cleaSync()
    }

    var isLogin: Boolean
        get() = dataStore.readBooleanData(IS_LOGIN, false)
        set(b) {
            dataStore.saveSyncBooleanData(IS_LOGIN, b)
        }
}
