package com.anne.play.ui.page.home

import androidx.paging.PagingData
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.viewmodel.BaseArticlePagingRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

/**
 *
 * Author:AnneLo
 * Time:2023/9/17
 */
class HomeArticlePagingRepository : BaseArticlePagingRepository() {
    override fun getPagingData(query: Query): Flow<PagingData<ArticleModel>> {
        TODO("Not yet implemented")
    }
}