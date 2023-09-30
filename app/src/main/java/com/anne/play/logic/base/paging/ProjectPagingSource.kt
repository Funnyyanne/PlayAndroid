package com.anne.play.logic.base.paging

import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.network.PlayAndroidNetwork

/**
 *
 * Author:AnneLo
 * Time:2023/9/30
 */
class ProjectPagingSource(private val cid: Int) : BasePagingSource() {
    override suspend fun getArticleList(page: Int): List<ArticleModel> {
        val apiResponse = PlayAndroidNetwork.getProject(page, cid)
        return apiResponse.data.datas
    }
}
