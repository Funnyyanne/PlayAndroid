package com.anne.play.logic.network

import com.anne.play.logic.network.service.HomePageService
import com.anne.play.logic.network.service.LoginService
import com.anne.play.logic.network.service.OfficialService
import com.anne.play.logic.network.service.ProjectService

/**
 *
 * Author:AnneLo
 * Time:2023/9/14
 */
object PlayAndroidNetwork {
    private val homeService = ServiceCreator.create(HomePageService::class.java)
    suspend fun getBanner() = homeService.getBanner()
    suspend fun getArticle(page: Int) = homeService.getArticle(page)

    private val projectService = ServiceCreator.create(ProjectService::class.java)
    suspend fun getProjectTree() = projectService.getProjectTree()
    suspend fun getProject(page: Int, cid: Int) = projectService.getProject(page, cid)
    private val officialService = ServiceCreator.create(OfficialService::class.java)
    suspend fun getWxArticleTree() = officialService.getWxArticleTree()
    suspend fun getWxArticle(page: Int, cid: Int) = officialService.getWxArticle(page, cid)

    private val loginService = ServiceCreator.create(LoginService::class.java)
    suspend fun getLogin(username: String, password: String) =
        loginService.getLogin(username, password)

    suspend fun getRegister(username: String, password: String, repassword: String) =
        loginService.getRegister(username, password, repassword)

    suspend fun getLogout() = loginService.getLogout()
}
