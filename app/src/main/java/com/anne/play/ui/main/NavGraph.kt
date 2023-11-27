package com.anne.play.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.anne.play.R
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.utils.getHtmlText
import com.anne.play.ui.main.PlayDestinations.ARTICLE_ROUTE_URL
import com.anne.play.ui.page.article.ArticlePage
import com.anne.play.ui.page.login.LoginPage
import com.anne.play.ui.page.login.LoginViewModel
import com.google.gson.Gson
import java.net.URLEncoder

/**
 *
 * Author:AnneLo
 * Time:2023/9/6
 */
object PlayDestinations {
    const val HOME_PAGE_ROUTE = "home_page_route"
    const val ARTICLE_ROUTE = "article_route"
    const val ARTICLE_ROUTE_URL = "article_route_url"
    const val LOGIN_ROUTE = "login_route"
}

class PlayActions(nav: NavHostController) {
    val enterArticle: (ArticleModel) -> Unit = { article ->
        article.desc = ""
        article.title = getHtmlText(article.title)
        val gson = Gson().toJson(article).trim()
        val result = URLEncoder.encode(gson, "utf-8")
        toAnimView(navController = nav, "${PlayDestinations.ARTICLE_ROUTE}/$result")
    }
    val toLogin: () -> Unit = {
        toAnimView(navController = nav, PlayDestinations.LOGIN_ROUTE)
    }

    val upPress: () -> Unit = {
        nav.navigateUp()
    }

    private fun toAnimView(navController: NavHostController, route: String) {
        navController.navigate(route) {
            anim {
                enter = R.anim.activity_push_in
                exit = R.anim.activity_push_out
                popEnter = R.anim.center_zoom_in
                popExit = R.anim.center_zoom_out
            }
        }
    }
}

@ExperimentalPagingApi
@Composable
fun NavGraph(startDestination: String = PlayDestinations.HOME_PAGE_ROUTE) {
    val navController = rememberNavController() // 获取NavController
    val actions = remember(navController) {
        PlayActions(navController)
    }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(PlayDestinations.HOME_PAGE_ROUTE) {
            // 默认跳转主页
            val viewModel: HomeViewModel = viewModel()
            val position by viewModel.position.observeAsState()
            MainPage(actions, position) { tab ->
                viewModel.onPositionChanged(tab)
            }
        }
        composable(PlayDestinations.LOGIN_ROUTE) {
            val viewModel: LoginViewModel = viewModel()
            val loginState by viewModel.state.observeAsState()
            LoginPage(
                actions,
                loginState,
                { viewModel.logout() },
            ) {
                viewModel.toLoginOrRegister(it)
            }
        }
        composable(
            "${PlayDestinations.ARTICLE_ROUTE}/{$ARTICLE_ROUTE_URL}",
            arguments = listOf(
                navArgument(ARTICLE_ROUTE_URL) {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val parcelable = arguments.getString(ARTICLE_ROUTE_URL)
            // 序列化参数 传给文章详情页
            val fromJson = Gson().fromJson(parcelable, ArticleModel::class.java)
            ArticlePage(article = fromJson, onBack = actions.upPress)
        }
    }
}
