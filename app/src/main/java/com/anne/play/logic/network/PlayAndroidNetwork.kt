package com.anne.play.logic.network

import com.anne.play.logic.network.service.HomePageService
import com.anne.play.logic.network.service.ProjectService

/**
 *
 * Author:AnneLo
 * Time:2023/9/14
 */
object PlayAndroidNetwork {
    private val homeService = ServiceCreator.create(HomePageService::class.java)
    suspend fun getBanner() = homeService.getBanner()
    suspend fun getArticleList(page: Int) = homeService.getArticle(page)
    suspend fun getArticle(page: Int) = homeService.getArticle(page)

    private val projectService = ServiceCreator.create(ProjectService::class.java)
    suspend fun getProjectTree() = projectService.getProjectTree()
    suspend fun getProject(page: Int, cid: Int) = projectService.getProject(page, cid)

}