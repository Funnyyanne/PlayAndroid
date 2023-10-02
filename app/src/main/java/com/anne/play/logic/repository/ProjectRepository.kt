package com.anne.play.logic.repository

import android.accounts.NetworkErrorException
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.anne.play.R
import com.anne.play.logic.base.paging.ProjectPagingSource
import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.ClassifyModel
import com.anne.play.logic.model.PlayState
import com.anne.play.logic.network.PlayAndroidNetwork
import com.anne.play.logic.utils.NetworkUtil
import com.anne.play.logic.utils.showToast
import com.anne.play.logic.viewmodel.Query

/**
 *
 * Author:AnneLo
 * Time:2023/9/29
 */
class ProjectRepository constructor(private val application: Application) :
    BaseArticleRepository(application) {
    @ExperimentalPagingApi
    override fun getPagingData(query: Query) = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
        ),
    ) {
        ProjectPagingSource(query.cid)
    }.flow

    override suspend fun getArticleTree(): BaseModel<List<ClassifyModel>> {
        return PlayAndroidNetwork.getProjectTree()
    }

    suspend fun getTree(state: MutableLiveData<PlayState<Any?>>) {
        state.postValue(PlayState.PlayLoading)
        if (!NetworkUtil.isConnected(application)) {
            showToast(application, R.string.no_network)
            state.postValue(PlayState.PlayError(NetworkErrorException(application.getString(R.string.no_network))))
            return
        }
        val tree = PlayAndroidNetwork.getProjectTree()
        if (tree.errorCode == 0) {
            val projectList = tree.data
            state.postValue(PlayState.PlaySuccess(projectList))
        } else {
            state.postValue(PlayState.PlayError(NetworkErrorException(application.getString(R.string.no_network))))
        }
    }
}
