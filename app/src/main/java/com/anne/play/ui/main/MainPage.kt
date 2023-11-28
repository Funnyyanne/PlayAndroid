package com.anne.play.ui.main

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.anne.play.R
import com.anne.play.logic.model.PlayState
import com.anne.play.ui.page.home.HomePage
import com.anne.play.ui.page.home.HomePageViewModel
import com.anne.play.ui.page.login.LoginViewModel
import com.anne.play.ui.page.login.LogoutDefault
import com.anne.play.ui.page.mine.ProfilePage
import com.anne.play.ui.page.offical.OfficialViewModel
import com.anne.play.ui.page.project.ArticleListPage
import com.anne.play.ui.page.project.ProjectViewModel

/**
 *
 * Author:AnneLo
 * Time:2023/9/6
 */
@ExperimentalPagingApi
@Composable
fun MainPage(
    actions: PlayActions,
    position: CourseTabs?,
    onPositionChanged: (CourseTabs) -> Unit,
) {
    val tabs = CourseTabs.values()

    Scaffold(backgroundColor = MaterialTheme.colors.primary, bottomBar = {
        BottomNavigation {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    modifier = Modifier.background(MaterialTheme.colors.primary),
                    icon = {
                        val painter: Painter = if (tab == position) {
                            painterResource(tab.selectIcon)
                        } else {
                            painterResource(tab.icon)
                        }
                        Icon(painter, contentDescription = null)
                    },
                    selected = tab == position,
                    onClick = { onPositionChanged(tab) },
                    alwaysShowLabel = true,
                )
            }
        }
    }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        val isLand = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

        Crossfade(targetState = position) { screen ->
            when (screen) {
                CourseTabs.HOME_PAGE -> {
                    val viewModel: HomePageViewModel = viewModel()
                    val bannerData by viewModel.bannerState.observeAsState(PlayState.PlayLoading)
                    val lazyPagingItems = viewModel.articleResult.collectAsLazyPagingItems()
                    HomePage(modifier, isLand, bannerData, lazyPagingItems, {
                        if (bannerData !is PlayState.PlaySuccess<*>) {
                            viewModel.getBanner()
                        }
                        if (lazyPagingItems.itemCount <= 0) {
                            viewModel.getHomeArticle()
                        }
                    }) { actions.enterArticle(it) }
                }

                CourseTabs.PROJECT, CourseTabs.OFFICIAL_ACCOUNT -> {
                    val viewModel = if (screen == CourseTabs.PROJECT) {
                        viewModel<ProjectViewModel>()
                    } else {
                        viewModel<OfficialViewModel>()
                    }
                    val lazyPagingItem = viewModel.articleResult.collectAsLazyPagingItems()
                    val tree by viewModel.treeLiveData.observeAsState(PlayState.PlayLoading)
                    val treePosition by viewModel.position.observeAsState(0)
                    ArticleListPage(
                        modifier,
                        tree,
                        treePosition,
                        lazyPagingItem,
                        {
                            viewModel.getDataList()
                        },
                        {
                            viewModel.searchArticle(it)
                        },
                        {
                            viewModel.onPositionChanged(it)
                        },
                    ) {
                        actions.enterArticle(it)
                    }
                }

                CourseTabs.MINE -> {
                    val viewModel: LoginViewModel = viewModel()
                    val logoutState by viewModel.logoutState.observeAsState(LogoutDefault)
                    ProfilePage(
                        modifier,
                        isLand = isLand,
                        actions.toLogin,
                        logoutState,
                        { viewModel.logout() },
                    ) {
                        actions.enterArticle(it)
                    }
                }

                else -> {}
            }
        }
    }
}

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val selectIcon: Int,
) {

    HOME_PAGE(
        R.string.home_page,
        R.drawable.ic_nav_news_normal,
        R.drawable.ic_nav_news_actived,
    ),
    PROJECT(
        R.string.project,
        R.drawable.ic_nav_tweet_normal,
        R.drawable.ic_nav_tweet_actived,
    ),
    OFFICIAL_ACCOUNT(
        R.string.official_account,
        R.drawable.ic_nav_discover_normal,
        R.drawable.ic_nav_discover_actived,
    ),
    MINE(
        R.string.mine,
        R.drawable.ic_nav_my_normal,
        R.drawable.ic_nav_my_pressed,
    ),
}
