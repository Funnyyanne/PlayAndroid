package com.anne.play.logic.network.service

import com.anne.play.logic.model.ArticleListModel
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.model.BannerBean
import com.anne.play.logic.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * Author:AnneLo
 * Time:2023/9/12
 */
interface HomePageService {
    @GET("banner/json")
    suspend fun getBanner(): BaseModel<List<BannerBean>>

    @GET("article/list/{a}/json")
    suspend fun getArticle(@Path("a") a: Int): BaseModel<ArticleListModel>
}