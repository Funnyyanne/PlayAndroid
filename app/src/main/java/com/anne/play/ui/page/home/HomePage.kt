package com.anne.play.ui.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.anne.play.R
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.model.BannerBean
import com.anne.play.logic.model.PlayState
import com.anne.play.ui.main.PlayActions
import com.anne.play.ui.page.article.list.ArticleListPaging
import com.anne.play.ui.view.PlayAppBar
import com.anne.play.ui.view.lce.LcePage
import com.zj.banner.BannerPager
import com.zj.banner.ui.indicator.NumberIndicator

@Composable
fun HomePage(
    actions: PlayActions,
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val bannerData by viewModel.bannerState.observeAsState(PlayState.PlayLoading)
    viewModel.getData()
    val lazyPagingItems = viewModel.articleResult.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    Column(modifier = modifier.fillMaxSize()) {
        PlayAppBar(title = stringResource(id = R.string.home_page), false)
        LcePage(
            playState = bannerData as PlayState.PlayLoading,
            onErrorClick = { viewModel.getData() },
        ) {
            val data = bannerData as PlayState.PlaySuccess<List<BannerBean>>
            BannerPager(items = data.data, indicator = NumberIndicator()) {
                actions.enterArticle(ArticleModel(title = it.title, link = it.url))
            }

            ArticleListPaging(
                listState = listState,
                lazyPagingItems = lazyPagingItems,
                enterArticle = actions.enterArticle,
            )
        }
    }
}
