package com.anne.play.ui.view

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anne.play.logic.model.ClassifyModel
import com.anne.play.logic.utils.getHtmlText

/**
 *
 * Author:AnneLo
 * Time:2023/10/1
 */
@Composable
fun ArticleTabRow(
    position: Int?,
    data: List<ClassifyModel>,
    onTabClick: (Int, Int, Boolean) -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = position ?: 0,
        modifier = Modifier.wrapContentWidth(),
        edgePadding = 3.dp,
        containerColor = MaterialTheme.colors.primary,
    ) {
        data.forEachIndexed { index, projectClassify ->
            Tab(
                text = { Text(getHtmlText(projectClassify.name)) },
                selected = position == index,
                onClick = {
                    onTabClick(index, projectClassify.id, false)
                },
            )
        }
        onTabClick(0, data[position ?: 0].id, true)
    }
}
