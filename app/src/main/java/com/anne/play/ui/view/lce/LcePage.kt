package com.anne.play.ui.view.lce

import androidx.compose.runtime.Composable
import com.anne.play.logic.model.*
/**
 *
 * Author:AnneLo
 * Time:2023/9/20
 */

@Composable
fun <T> LcePage(
    playState: PlayState<T>,
    content: @Composable (data: T) -> Unit,
    onErrorClick: () -> Unit,
) {
    when (playState) {
        is PlayState.PlayLoading -> {
            LoadingContent()
        }

        is PlayState.PlayError -> {
            ErrorContent(onErrorClick = onErrorClick)
        }

        is PlayState.PlaySuccess -> {
            content(playState.data)
        }

        else -> {}
    }
}
