package com.anne.play.logic.base.repository

import android.accounts.NetworkErrorException
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.anne.play.R
import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.ClassifyModel
import com.anne.play.logic.model.PlayState
import com.anne.play.logic.utils.NetworkUtil
import com.anne.play.logic.utils.showToast

/**
 *
 * Author:AnneLo
 * Time:2Ï023/9/30
 */
abstract class BaseArticleRepository(private val application: Application) :
    BaseArticlePagingRepository() {

    /**
     * 获取标题列表
     */
    suspend fun getTree(state: MutableLiveData<PlayState<List<ClassifyModel>>>) {
        state.postValue(PlayState.PlayLoading)
        if (!NetworkUtil.isConnected(application)) {
            showToast(application, R.string.no_network)
            state.postValue(PlayState.PlayError(NetworkErrorException(application.getString(R.string.no_network))))
            return
        }
        val tree = getArticleTree()
        if (tree.errorCode == 0) {
            val projectList = tree.data
            state.postValue(PlayState.PlaySuccess(projectList))
        } else {
            state.postValue(PlayState.PlayError(NetworkErrorException(application.getString(R.string.no_network))))
        }
    }
    abstract suspend fun getArticleTree(): BaseModel<List<ClassifyModel>>
}
