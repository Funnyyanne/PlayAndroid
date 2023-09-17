package com.anne.play.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.anne.play.logic.model.ArticleModel
import com.anne.play.ui.main.PlayDestonations.ARTICLE_ROUTE_URL
import com.google.gson.Gson
import java.net.URLEncoder

/**
 *
 * Author:AnneLo
 * Time:2023/9/6
 */
object PlayDestonations {
    const val HOME_PAGE_ROUTE = "home_page_route"
    const val ARTICLE_ROUTE = "article_route"
    const val ARTICLE_ROUTE_URL = "article_route_url"
    const val LOGIN_ROUTE = "login_route"

}

class PlayActions(nav: NavHostController) {
    val enterArticle: (ArticleModel) -> Unit = { article ->
        val gson = Gson().toJson(article).trim()
        val result = URLEncoder.encode(gson, "utf-8")
        nav.navigate("${PlayDestonations.ARTICLE_ROUTE}/$result")
    }
    val upPass: () -> Unit = {
        nav.navigateUp()
    }
}

@ExperimentalPagingApi
@Composable
fun NavGraph(startDestination: String = PlayDestonations.HOME_PAGE_ROUTE) {
    val navController = rememberNavController()  //获取NavController
    val actions = remember(navController) {
        PlayActions(navController)
    }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(PlayDestonations.HOME_PAGE_ROUTE) {
            //默认跳转主页
            //MainPage(actions)
        }
        composable(
            "${PlayDestonations.ARTICLE_ROUTE}/{$ARTICLE_ROUTE_URL}", arguments = listOf(
                navArgument(ARTICLE_ROUTE_URL) {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val parcelable = arguments.getString(ARTICLE_ROUTE_URL)
            //序列化参数 传给文章详情页
            val fromJson = Gson().fromJson(parcelable, ArticleModel::class.java)
            //ArticlePage(article=fromJson,onBack=article.upPress)
        }
    }
}