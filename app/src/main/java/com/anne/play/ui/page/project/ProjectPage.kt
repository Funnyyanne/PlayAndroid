package com.anne.play.ui.page.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.model.ClassifyModel
import com.anne.play.logic.model.PlayState
import com.anne.play.logic.model.Query
import com.anne.play.ui.page.article.list.ArticleListPaging
import com.anne.play.ui.view.ArticleTabRow
import com.anne.play.ui.view.lce.LcePage
import dev.chrisbanes.accompanist.insets.statusBarsHeight

@Composable
fun ArticleListPage(
    modifier: Modifier,
    tree: PlayState<List<ClassifyModel>>,
    position: Int?,
    lazyPagingItems: LazyPagingItems<ArticleModel>,
    loadData: () -> Unit,
    searchArticle: (Query) -> Unit,
    onPositionChanged: (Int) -> Unit,
    enterArticle: (ArticleModel) -> Unit,

) {
    val listState = rememberLazyListState()
    var loadState by rememberSaveable { mutableStateOf(false) }
    var loadPageState by rememberSaveable {
        mutableStateOf(false)
    }
    if (!loadState) {
        loadState = true
        loadData()
    }
    Column(modifier = Modifier.background(color = MaterialTheme.colors.primary)) {
        Spacer(modifier = Modifier.statusBarsHeight())
        LcePage(playState = tree, onErrorClick = {
            loadData()
            loadState = true
        }) { data ->
            ArticleTabRow(position = position, data = data) { index, id, isFirst ->
                if (!isFirst) {
                    searchArticle(Query(id))
                    onPositionChanged(index)
                } else {
                    if (position == 0 && !loadPageState) {
                        searchArticle(Query(id))
                    }
                }
                loadPageState = true
            }
            ArticleListPaging(modifier, listState, lazyPagingItems, enterArticle)
        }
    }
}
