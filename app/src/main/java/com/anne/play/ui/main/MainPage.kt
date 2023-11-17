package com.anne.play.ui.main

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.anne.play.R
import com.anne.play.logic.model.PlayState
import com.anne.play.ui.page.article.OfficialAccountPage
import com.anne.play.ui.page.home.HomePage
import com.anne.play.ui.page.login.LoginViewModel
import com.anne.play.ui.page.login.LogoutDefault
import com.anne.play.ui.page.mine.ProfilePage
import com.anne.play.ui.page.project.ArticleListPage
import com.anne.play.ui.page.project.ProjectViewModel
import java.util.Locale

/**
 *
 * Author:AnneLo
 * Time:2023/9/6
 */
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalPagingApi
@Composable
fun MainPage(
    actions: PlayActions,
    viewModel: HomeViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val tabs = CourseTabs.values()
//    val viewModel: HomeViewModel = hiltViewModel()

    val position by viewModel.position.observeAsState()
    Scaffold(Modifier.background(MaterialTheme.colorScheme.primary), bottomBar = {
        BottomNavigation {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                    icon = { Icon(painterResource(id = tab.ordinal), contentDescription = null) },
                    selected = tab == position,
                    onClick = { viewModel.onPositionChanged(tab) },
                    label = { Text(text = stringResource(id = tab.title).uppercase(Locale.ROOT)) },
                )
            }
        }
    }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        val isLand = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

        Crossfade(targetState = position, label = "") { screen ->
            when (screen) {
                CourseTabs.HOME_PAGE -> HomePage(actions, modifier)
                CourseTabs.PROJECT -> {
                    val projectViewModel = viewModel<ProjectViewModel>()
                    val lazyPagingItem = projectViewModel.articleResult.collectAsLazyPagingItems()
                    val tree by projectViewModel.treeLiveData.observeAsState(PlayState.PlayLoading)
                    val treePosition by projectViewModel.position.observeAsState(0)
                    ArticleListPage(
                        modifier,
                        tree,
                        treePosition,
                        lazyPagingItem,
                        {
                            projectViewModel.getDataList()
                        },
                        {
                            projectViewModel.searchArticle(it)
                        },
                        {
                            projectViewModel.onPositionChanged(it)
                        },
                    ) {
                        actions.enterArticle(it)
                    }
                }

                CourseTabs.OFFICIAL_ACCOUNT -> OfficialAccountPage(actions.enterArticle, modifier)
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

enum class CourseTabs(@StringRes val title: Int, val icon: ImageVector) {

    HOME_PAGE(R.string.home_page, Icons.Default.Home),
    PROJECT(R.string.project, Icons.Filled.Info),
    OFFICIAL_ACCOUNT(R.string.official_account, Icons.Filled.DateRange),
    MINE(R.string.mine, Icons.Default.AccountCircle),
}
