package com.anne.play.ui.page.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anne.play.logic.model.PlayState
import com.anne.play.logic.viewmodel.BaseArticlePagingRepository
import com.anne.play.logic.viewmodel.BaseArticleViewModel
import com.anne.play.logic.viewmodel.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *
 * Author:AnneLo
 * Time:2023/9/19
 */
class HomePageViewModel(
    application: Application,
) : BaseArticleViewModel(application) {
    override val repositoryArticle: BaseArticlePagingRepository
        get() = HomeArticlePagingRepository()

    private var bannerJob: Job? = null
    private val _bannerState = MutableLiveData<PlayState<Any?>>()

    val bannerState: LiveData<PlayState<Any?>>
        get() = _bannerState

    fun getData() {
        getBanner()
        searchArticle(Query())
    }

    private fun getBanner() {
        bannerJob?.cancel()
        bannerJob = viewModelScope.launch(Dispatchers.IO) {
            (repositoryArticle as HomeArticlePagingRepository).getBanner(_bannerState)
        }
    }
}
