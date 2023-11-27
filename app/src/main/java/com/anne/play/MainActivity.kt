package com.anne.play

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.paging.ExperimentalPagingApi
import com.anne.play.logic.utils.setAndroidNativeLightStatusBar
import com.anne.play.logic.utils.transparentStatusBar
import com.anne.play.ui.main.NavGraph
import com.anne.play.ui.theme.PlayAndroidTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setAndroidNativeLightStatusBar()
        setContent {
            PlayAndroidTheme {
                ProvideWindowInsets {
                    NavGraph()
                }
            }
        }
    }
}
