package com.anne.play.ui.page.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.LoginModel
import com.anne.play.logic.model.PlayState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * Author:AnneLo
 * Time:2023/10/2
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val accountRepository: AccountRepository = AccountRepository()
    private val _state: MutableLiveData<PlayState<LoginModel>>()
    val state: LiveData<PlayState<LoginModel>>
        get() = _state

    fun toLoginOrRegister(account: Account) {
        _state.postValue(PlayState.PlayLoading)
        viewModelScope.launch(Dispatchers.IO) {
            val loginModel: BaseModel<LoginModel> = if (account.isLogin) {
                accountRepository.getLogin(account.username, account.password)
            } else {
                accountRepository.getRegister(account.username, account.password, account.password)
            }
            if (loginModel.errorCode == 0){
                val login = loginModel.data
                _state.postValue(PlayState.PlaySuccess(login))
                Play.is
            }
        }
    }
}

sealed class LogoutState
object LogoutFinish : LogoutState()
object LogoutDefault : LogoutState()
data class Account(val username: String, val password: String, val isLogin: Boolean)

