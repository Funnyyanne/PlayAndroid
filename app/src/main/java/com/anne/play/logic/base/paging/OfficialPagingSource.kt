package com.anne.play.logic.base.paging

import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.network.PlayAndroidNetwork

/**
 *
 * Author:AnneLo
 * Time:2023/10/2
 */
class OfficialPagingSource(private val cid: Int) : BasePagingSource() {
    override suspend fun getArticleList(page: Int): List<ArticleModel> {
        val apiResponse = PlayAndroidNetwork.getWxArticle(page, cid)
        return apiResponse.data.datas
    }
}
