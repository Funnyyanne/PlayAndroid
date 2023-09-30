package com.anne.play.ui.view.lce

import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.anne.play.R

/**
 *
 * Author:AnneLo
 * Time:2023/9/20
 */
@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AndroidView(
            { ProgressBar(it) },
            modifier = Modifier
                .width(200.dp)
                .height(110.dp),
        ) {
            it.indeterminateDrawable = AppCompatResources.getDrawable(it.context, R.drawable.loading_animation)
        }
    }
}
