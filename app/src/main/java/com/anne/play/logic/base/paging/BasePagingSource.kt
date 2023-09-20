package com.anne.play.logic.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anne.play.logic.model.ArticleModel

/**
 *
 * Author:AnneLo
 * Time:2023/9/17
 */
abstract class BasePagingSource : PagingSource<Int, ArticleModel>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? = null
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        return try {
            val page = params.key ?: 1
            val articleList = getArticleList(page)
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (articleList.isEmpty()) page + 1 else null
            LoadResult.Page(articleList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    abstract suspend fun getArticleList(page: Int): List<ArticleModel>

}