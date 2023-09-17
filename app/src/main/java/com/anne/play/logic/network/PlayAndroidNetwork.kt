package com.anne.play.logic.network

import com.anne.play.logic.network.service.HomePageService

/**
 *
 * Author:AnneLo
 * Time:2023/9/14
 */
object PlayAndroidNetwork {
    private val homeService = ServiceCreator.create(HomePageService::class.java)
    suspend fun getBanner() = homeService.getBanner()
    suspend fun getArticleList(page: Int) = homeService.getArticle(page)

}