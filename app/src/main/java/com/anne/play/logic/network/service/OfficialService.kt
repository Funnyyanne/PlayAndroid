package com.anne.play.logic.network.service

import com.anne.play.logic.model.ArticleListModel
import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.ClassifyModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * Author:AnneLo
 * Time:2023/9/30
 */
interface OfficialService {

    @GET("wxarticle/chapters/json")
    fun getWxArticleTree(): BaseModel<List<ClassifyModel>>

    @GET("wxarticle/list/{cid}/{page}/json")
    suspend fun getWxArticle(
        @Path("page") page: Int,
        @Path("cid") cid: Int,
    ): BaseModel<ArticleListModel>
}
