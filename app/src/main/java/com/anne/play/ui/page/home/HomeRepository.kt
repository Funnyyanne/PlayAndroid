package com.anne.play.ui.page.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.anne.play.logic.base.paging.HomePagingSource
import com.anne.play.logic.model.PlayState
import com.anne.play.logic.network.PlayAndroidNetwork
import com.anne.play.logic.viewmodel.BaseArticlePagingRepository
import java.lang.RuntimeException

/**
 *
 * Author:AnneLo
 * Time:2023/9/17
 */
class HomeArticlePagingRepository : BaseArticlePagingRepository() {
    override fun getPagingData(query: com.anne.play.logic.viewmodel.Query) =
        Pager(PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)) {
            HomePagingSource()
        }.flow

    suspend fun getBanner(state: MutableLiveData<PlayState<Any?>>) {
        state.postValue(PlayState.PlayLoading)
        val bannerResponse = PlayAndroidNetwork.getBanner()
        if (bannerResponse.errorCode == 0) {
            val bannerList = bannerResponse.data
            bannerList.forEach {
                it.data = it.imagePath
            }
            state.postValue(PlayState.PlaySuccess(bannerList))
        } else {
            state.postValue(PlayState.PlayError(RuntimeException("response status is ${bannerResponse.errorCode} msg is ${bannerResponse.errorMsg}")))
        }
    }
}
