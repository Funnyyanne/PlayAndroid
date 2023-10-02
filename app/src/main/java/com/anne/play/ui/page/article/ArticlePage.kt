package com.anne.play.ui.page.article

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.anne.play.R
import com.anne.play.logic.model.ArticleModel
import com.anne.play.logic.utils.getHtmlText
import com.anne.play.ui.view.PlayAppBar
import com.anne.play.ui.view.rememberWebViewWithLifecycle

/**
 *
 * Author:AnneLo
 * Time:2023/10/2
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ArticlePage(
    article: ArticleModel?,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val webView = rememberWebViewWithLifecycle()

    Scaffold(
        topBar = {
            PlayAppBar(title = getHtmlText(article?.title ?: "文章详情"), click = {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    onBack.invoke()
                }
            }, showRight = true, rightImg = Icons.Filled.Share, rightClick = {
                sharePost(article?.title, article?.link, context)
            })
        },
        content = {
            it
            AndroidView(
                factory = { webView },
                modifier = Modifier
                    .fillMaxSize(),
            ) { view ->
                view.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                        return try {
                            if (url.startsWith("http:") || url.startsWith("https:")) {
                                view!!.loadUrl(url)
                            } else {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                webView.context.startActivity(intent)
                            }
                            true
                        } catch (e: Exception) {
                            false
                        }
                    }
                }
                val settings: WebSettings = view.settings
                settings.mixedContentMode
                WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                settings.javaScriptEnabled = true
                settings.blockNetworkImage = false
                view.loadUrl(article?.link ?: "")
            }
        },
    )
}

fun sharePost(title: String?, post: String?, context: Context) {
    if (title == null || post == null) {
        return
    }
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, title)
        putExtra(Intent.EXTRA_TEXT, post)
    }
    context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_article)))
}

@Preview(showBackground = true)
@Composable
fun ArticlePagePreview() {
    ArticlePage(
        ArticleModel(
            title = "标题",
            superChapterName = "分类",
            author = "作者",
        ),
    ) {
        // 回调
    }
}
