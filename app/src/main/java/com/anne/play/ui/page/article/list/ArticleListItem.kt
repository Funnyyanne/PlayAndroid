package com.anne.play.ui.page.article.list

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anne.play.R
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.utils.getHtmlText

/**
 *
 * Author:AnneLo
 * Time:2023/9/22
 */
@Composable
fun ArticleItem(
    article: ArticleModel?,
    enterArticle: (urlArgs: ArticleModel) -> Unit,
) {
    if (article == null) return
//    NewArticleItem(article = article){
//        enterArticle(article)
//    }
}

@Composable
fun ArticleListItem(
    article: ArticleModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = 1.dp,
    titleStyle: TextStyle = MaterialTheme.typography.subtitle1,
) {
    Surface(elevation = elevation, shape = shape, modifier = modifier) {
        Row(modifier = Modifier.clickable(onClick = onClick)) {
            if (article.envelopePic.isNotBlank()) {
//                NetworkImage()
            } else {
                Image(
                    painterResource(id = R.drawable.img_default),
                    contentDescription = null,
                    modifier = Modifier
                        .height(96.dp)
                        .width(91.5.dp),
                )
            }
            Column {
                Text(text = getHtmlText(article.title))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = article.superChapterName)
                    Text(text = if (TextUtils.isEmpty(article.author)) article.shareUser else article.author)
                }
            }
        }
    }
}
