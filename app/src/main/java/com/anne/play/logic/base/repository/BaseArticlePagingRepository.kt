package com.anne.play.logic.base.repository

import androidx.paging.PagingData
import com.anne.play.logic.model.ArticleModel
import kotlinx.coroutines.flow.Flow

/**
 *
 * Author:AnneLo
 * Time:2023/9/30
 */
abstract class BaseArticlePagingRepository {
    companion object {
        const val PAGE_SIZE = 15
    }

    abstract fun getPagingData(query: com.anne.play.logic.model.Query): Flow<PagingData<ArticleModel>>
}
