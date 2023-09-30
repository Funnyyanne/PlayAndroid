package com.anne.play.logic.viewmodel

import androidx.paging.PagingData
import com.anne.play.logic.model.ArticleModel
import kotlinx.coroutines.flow.Flow

abstract class BaseArticlePagingRepository {
    companion object {
        const val PAGE_SIZE = 15
    }

    abstract fun getPagingData(query: Query): Flow<PagingData<ArticleModel>>
}

// data class 查询类
data class Query(val cid: Int = -1, val k: String = "")
