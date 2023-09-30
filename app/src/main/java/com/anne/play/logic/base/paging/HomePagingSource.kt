package com.anne.play.logic.base.paging

import androidx.paging.PagingState
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.network.PlayAndroidNetwork

/**
 *
 * Author:AnneLo
 * Time:2023/9/17
 */
class HomePagingSource : BasePagingSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        return try {
            val page = params.key ?: 1 // set page 1 as default
            val apiResponse = PlayAndroidNetwork.getArticle(page)
            val articleList = apiResponse.data.datas
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (articleList.isNotEmpty()) page + 1 else null
            LoadResult.Page(articleList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? = null

    override suspend fun getArticleList(page: Int): List<ArticleModel> {
        TODO("Not yet implemented")
    }
}
