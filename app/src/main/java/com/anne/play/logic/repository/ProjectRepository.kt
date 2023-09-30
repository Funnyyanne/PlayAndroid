package com.anne.play.logic.repository

import android.accounts.NetworkErrorException
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.anne.play.R
import com.anne.play.logic.base.paging.ProjectPagingSource
import com.anne.play.logic.network.PlayAndroidNetwork
import com.anne.play.logic.utils.NetworkUtil
import com.anne.play.logic.utils.showToast
import com.anne.play.logic.viewmodel.BaseArticlePagingRepository
import com.anne.play.logic.viewmodel.Query
import com.anne.play.ui.page.home.PlayError
import com.anne.play.ui.page.home.PlayLoading
import com.anne.play.ui.page.home.PlayState
import com.anne.play.ui.page.home.PlaySuccess

/**
 *
 * Author:AnneLo
 * Time:2023/9/29
 */
class ProjectRepository constructor(private val application: Application) : BaseArticlePagingRepository() {
    @ExperimentalPagingApi
    override fun getPagingData(query: Query) = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
        ),
    ) {
        ProjectPagingSource(query.cid)
    }.flow

    suspend fun getTree(state: MutableLiveData<PlayState>) {
        state.postValue(PlayLoading)
        if (!NetworkUtil.isConnected(application)) {
            showToast(application, R.string.no_network)
            state.postValue(PlayError(NetworkErrorException(application.getString(R.string.no_network))))
            return
        }
        val tree = PlayAndroidNetwork.getProjectTree()
        if (tree.errorCode == 0) {
            val projectList = tree.data
            state.postValue(PlaySuccess(projectList))
        } else {
            state.postValue(PlayError(NetworkErrorException(application.getString(R.string.no_network))))
        }
    }
}
