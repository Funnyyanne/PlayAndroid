package com.anne.play.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.statusBarsHeight

/**
 *
 * Author:AnneLo
 * Time:2023/9/20
 */
@Composable
fun PlayAppBar(
    title: String,
    showBack: Boolean = true,
    click: (() -> Unit)? = null,
    showRight: Boolean = false,
    rightImg: ImageVector = Icons.Rounded.MoreVert,
    rightClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colors.primary)) {
        Spacer(modifier = Modifier.statusBarsHeight())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (showBack && click != null) {
                IconButton(
                    modifier = Modifier.wrapContentWidth(Alignment.Start),
                    onClick = click
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "back")
                }
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = title,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis
            )
            if (showRight && rightClick != null) {
                IconButton(
                    onClick = rightClick,
                    modifier = Modifier.wrapContentWidth(Alignment.End)
                ) {
                    Icon(imageVector = rightImg, contentDescription = "more")

                }
            }
        }
    }

}