package com.anne.play.ui.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.anne.play.R
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.model.BannerBean
import com.anne.play.logic.model.PlayState
import com.anne.play.ui.page.article.list.ArticleListPaging
import com.anne.play.ui.view.PlayAppBar
import com.anne.play.ui.view.lce.LcePage
import com.zj.banner.BannerPager
import com.zj.banner.ui.indicator.CircleIndicator
import com.zj.banner.ui.indicator.Indicator
import com.zj.banner.ui.indicator.NumberIndicator

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    isLand: Boolean = false,
    bannerData: PlayState<List<BannerBean>>,
    lazyPagingItems: LazyPagingItems<ArticleModel>,
    loadData: () -> Unit,
    toArticleDetails: (ArticleModel) -> Unit,
) {
    var loadArticleState by rememberSaveable { mutableStateOf(false) }
    if (!loadArticleState) {
        loadArticleState = true
        loadData()
    }
    val listState = rememberLazyListState()

    Column(modifier = modifier.fillMaxSize()) {
        PlayAppBar(title = stringResource(id = R.string.home_page), false)
        LcePage(
            playState = bannerData,
            onErrorClick = { loadData() },
        ) { data ->
            loadArticleState = true
            if (isLand) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    val bannerModifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(15.dp)
                    val articleModifier = Modifier.weight(1.5f)
                    HomeContent(
                        data,
                        bannerModifier,
                        articleModifier,
                        NumberIndicator(),
                        lazyPagingItems,
                        toArticleDetails,
                    )
                }
            } else {
                HomeContent(
                    data = data,
                    lazyPagingItems = lazyPagingItems,
                    toArticleDetails = toArticleDetails,
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    data: List<BannerBean>,
    modifier: Modifier = Modifier,
    articleModifier: Modifier = Modifier,
    indicator: Indicator = CircleIndicator(),
    lazyPagingItems: LazyPagingItems<ArticleModel>,
    toArticleDetails: (ArticleModel) -> Unit,
) {
    val listState = rememberLazyListState()
    BannerPager(items = data, modifier = modifier, indicator = indicator) {
        toArticleDetails(ArticleModel(title = it.title, link = it.url))
    }

    ArticleListPaging(
        articleModifier,
        listState,
        lazyPagingItems,
        toArticleDetails,
    )
}
