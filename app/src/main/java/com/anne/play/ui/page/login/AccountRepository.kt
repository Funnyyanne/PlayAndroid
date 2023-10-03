package com.anne.play.ui.page.login

import com.anne.play.logic.network.PlayAndroidNetwork

class AccountRepository {
    suspend fun getLogin(username: String, password: String) = PlayAndroidNetwork.getLogin(username, password)
    suspend fun getRegister(username: String, password: String, repassword: String) = PlayAndroidNetwork.getRegister(username, password, repassword)
    suspend fun getLogout() = PlayAndroidNetwork.getLogout()
}
