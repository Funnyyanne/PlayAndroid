package com.anne.play.ui.page.mine

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anne.play.Play
import com.anne.play.R
import com.anne.play.logic.model.ArticleModel
import com.anne.play.ui.page.login.LogoutDefault
import com.anne.play.ui.page.login.LogoutFinish
import com.anne.play.ui.page.login.LogoutState

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    isLand: Boolean = false,
    toLogin: () -> Unit,
    logoutState: LogoutState,
    logout: () -> Unit,
    enterArticle: (ArticleModel) -> Unit,
) {
    if (isLand) {
        Row(modifier = modifier.fillMaxSize()) {
            Image(
                painterResource(R.drawable.img_default),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.5f)
                    .verticalScroll(rememberScrollState()),
            ) {
            }
        }
    }
}

@Composable
fun UserInfoFields(
    toLogin: () -> Unit,
    enterArticle: (ArticleModel) -> Unit,
    logoutState: LogoutState,
    logout: () -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        when (logoutState) {
            LogoutDefault -> {
                NameAndPosition(true, toLogin)
            }

            LogoutFinish -> {
                NameAndPosition(false, toLogin)
            }
        }

        ProfileProperty(
            ArticleModel(
                title = stringResource(id = R.string.my_blog),
                link = "https://annelo.eu.org/",
            ),
            enterArticle = enterArticle,
        )
        ProfileProperty(
            ArticleModel(
                title = stringResource(R.string.my_nuggets),
                link = "https://juejin.cn/user/4077436296244813/posts",
            ),
            enterArticle,
        )
        ProfileProperty(
            ArticleModel(
                title = stringResource(R.string.my_github),
                link = "https://github.com/Funnyyanne/PlayAndroid",
            ),
            enterArticle,
        )
    }
}

@Composable
fun ProfileProperty(atricle: ArticleModel, enterArticle: (ArticleModel) -> Unit) {
    Column(modifier = Modifier.clickable { enterArticle(atricle) }) {
        Divider()
        Row(
            modifier = Modifier.fillMaxWidth().height(55.dp).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = atricle.title,
                modifier = Modifier.weight(1f),
                style = androidx.compose.material.MaterialTheme.typography.subtitle2,
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.wrapContentWidth(Alignment.End),
            )
        }
    }
}

@Composable
fun NameAndPosition(refresh: Boolean, toLogin: () -> Unit) {
    Column(
        modifier = if (Play.isLogin) {
            Modifier.padding(16.dp)
        } else {
            Modifier
                .padding(16.dp)
                .clickable { toLogin() }
        },
    ) {
        Text(
            text = if (Play.isLogin && refresh) Play.nickname else stringResource(id = R.string.no_login),
            modifier = Modifier.height(32.dp),
            style = androidx.compose.material.MaterialTheme.typography.h5,
        )
        Text(
            text = if (Play.isLogin && refresh) {
                Play.username
            } else {
                stringResource(id = R.string.click_login)
            },
            modifier = Modifier.padding(bottom = 20.dp).height(24.dp),
            style = androidx.compose.material.MaterialTheme.typography.body1,
        )
    }
}
