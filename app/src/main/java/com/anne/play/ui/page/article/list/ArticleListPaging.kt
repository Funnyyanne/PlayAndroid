package com.anne.play.ui.page.article.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.utils.showToast
import com.anne.play.ui.view.lce.ErrorContent
import com.anne.play.ui.view.lce.LoadingContent

/**
 *
 * Author:AnneLo
 * Time:2023/9/22
 */

@Composable
fun ArticleListPaging(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    lazyPagingItems: LazyPagingItems<ArticleModel>,
    enterArticle: (ArticleModel) -> Unit,
) {
    val context = LocalContext.current
    LazyColumn(modifier = modifier, state = listState) {
        items(lazyPagingItems.itemSnapshotList) { article ->
            ArticleItem(article) { urlArgs ->
                enterArticle(urlArgs)
            }
        }
        val loadStates = lazyPagingItems.loadState
        when {
            loadStates.refresh is LoadState.Loading -> {
                item { LoadingContent(modifier = Modifier.fillParentMaxSize()) }
            }

            loadStates.append is LoadState.Loading -> {
                item { LoadingContent() }
            }

            loadStates.refresh is LoadState.Error -> {
                val e = lazyPagingItems.loadState.refresh as LoadState.Error
                showToast(context, e.error.localizedMessage ?: "未知错误")
                item {
                    ErrorContent(modifier = Modifier.fillMaxSize()) {
                        lazyPagingItems.retry()
                    }
                }
            }

//            loadStates.append is LoadState.Error -> {
//                val e = lazyPagingItems.loadState.refresh as LoadState.Error
//                showToast(context, e.error.localizedMessage ?: "")
//                item {
//                    ErrorContent(modifier = Modifier.fillMaxSize()) {
//                        lazyPagingItems.retry()
//                    }
//                }
//            }

            loadStates.append is LoadState.Error -> {
                val e = lazyPagingItems.loadState.refresh as LoadState.Error
                showToast(context, e.error.localizedMessage ?: "")
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(onClick = { lazyPagingItems.retry() }) {
                            Text(text = "Retry")
                        }
                    }
                }
            }

            else -> {}
        }
    }
}
