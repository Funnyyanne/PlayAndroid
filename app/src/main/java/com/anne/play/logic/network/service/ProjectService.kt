package com.anne.play.logic.network.service

import com.anne.play.logic.model.ArticleListModel
import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.ClassifyModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * Author:AnneLo
 * Time:2023/9/29
 */
interface ProjectService {
    @GET("project/tree/json")
    suspend fun getProjectTree(): BaseModel<List<ClassifyModel>>

    @GET("project/list/{page}/json")
    suspend fun getProject(
        @Path("page") page: Int,
        @Query("cid") cid: Int,
    ): BaseModel<ArticleListModel>
}
