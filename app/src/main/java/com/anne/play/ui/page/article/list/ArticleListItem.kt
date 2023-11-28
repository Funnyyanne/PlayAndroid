package com.anne.play.ui.page.article.list

import android.text.TextUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anne.play.R
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.utils.getHtmlText
import com.zj.banner.utils.ImageLoader

//
// /**
// *
// * Author:AnneLo
// * Time:2023/9/22
// */
// // @Composable
// // fun ArticleItem(
// //    article: ArticleModel?,
// //    enterArticle: (urlArgs: ArticleModel) -> Unit,
// // ) {
// //    if (article == null) return
// // //    NewArticleItem(article = article){
// // //        enterArticle(article)
// // //    }
// // }
//
// @Composable
// fun ArticleListItem(
//    article: ArticleModel,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    shape: Shape = RectangleShape,
//    elevation: Dp = 1.dp,
//    titleStyle: TextStyle = MaterialTheme.typography.subtitle1,
// ) {
//    Surface(elevation = elevation, shape = shape, modifier = modifier) {
//        Row(modifier = Modifier.clickable(onClick = onClick)) {
//            if (article.envelopePic.isNotBlank()) {
// //                NetworkImage()
//            } else {
//                Image(
//                    painterResource(id = R.drawable.img_default),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .height(96.dp)
//                        .width(91.5.dp),
//                )
//            }
//            Column {
//                Text(text = getHtmlText(article.title))
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(text = article.superChapterName)
//                    Text(text = if (TextUtils.isEmpty(article.author)) article.shareUser else article.author)
//                }
//            }
//        }
//    }
// }
//
// @Composable
// fun ArticleModel?.ArticleItem(
//    enterArticle: (urlArgs: ArticleModel) -> Unit,
// ) {
//    if (this == null) return
//    ArticleListItem(
//        article = this,
//        onClick = { enterArticle(this) },
//        modifier = Modifier
//            .height(96.dp)
//            .padding(start = 16.dp, top = 8.dp),
//        shape = RoundedCornerShape(topStart = 24.dp),
//    )
// }

@Composable
fun ArticleItem(
    article: ArticleModel?,
    enterArticle: (urlArgs: ArticleModel) -> Unit,
) {
    if (article == null) return
//    ArticleListItem(
//        article = article,
//        onClick = { enterArticle(article) },
//        modifier = Modifier.height(96.dp).padding(start = 16.dp, top = 8.dp),
//        shape = RoundedCornerShape(topStart = 24.dp)
//    )
    NewArticleListItem(article = article) {
        enterArticle(article)
    }
}

@Composable
fun ArticleListItem(
    article: ArticleModel,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    onClick: () -> Unit,
) {
    Surface(
        elevation = 1.dp,
        shape = shape,
        modifier = modifier,
    ) {
        Row(modifier = Modifier.clickable(onClick = onClick)) {
            ImageLoader(
                if (article.envelopePic.isNotBlank()) {
                    article.envelopePic
                } else {
                    R.drawable.img_default
                },
                Modifier.aspectRatio(1f),
            )
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                ),
            ) {
                Text(
                    text = getHtmlText(article.title),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 4.dp),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = article.superChapterName,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start),
                    )
                    Text(
                        text = if (TextUtils.isEmpty(article.author)) article.shareUser else article.author,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start),
                    )
                }
            }
        }
    }
}

@Composable
fun NewArticleListItem(
    article: ArticleModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val hasImage = article.envelopePic.isNotBlank()
    Card(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = if (hasImage) 7.dp else 5.dp)
            .fillMaxWidth()
            .height(if (hasImage) 90.dp else 70.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    arrayListOf(
                        colorResource(R.color.article_item_bg_start),
                        colorResource(R.color.article_item_bg_mid),
                        colorResource(R.color.article_item_bg_end),
                    ),
                ),
            ).clickable(onClick = onClick),
        ) {
            if (hasImage) {
                ImageLoader(
                    article.envelopePic,
                    Modifier.aspectRatio(1f),
                )
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
            ) {
                Text(
                    text = getHtmlText(article.title),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = if (hasImage) 2 else 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(Alignment.Top),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(Alignment.Bottom),
                ) {
                    Text(
                        text = article.superChapterName,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start),
                    )
                    Text(
                        text = if (TextUtils.isEmpty(article.author)) article.shareUser else article.author,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItemPreview() {
    ArticleListItem(
        article = ArticleModel(
            title = "标题",
            superChapterName = "分类",
            author = "作者",
        ),
        modifier = Modifier.height(96.dp).padding(start = 16.dp, top = 8.dp),
        shape = RoundedCornerShape(topStart = 24.dp),
    ) {
        // 回调
    }
}

@Preview(showBackground = true)
@Composable
fun NewArticleItemPreview() {
    NewArticleListItem(
        ArticleModel(
            title = "标题",
            superChapterName = "分类",
            author = "作者",
        ),
    ) {
        // 回调
    }
}
