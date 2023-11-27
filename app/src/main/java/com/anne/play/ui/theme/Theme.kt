package com.anne.play.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = green900,
    primaryVariant = purple700,
    secondary = green300,
    background = gray,
    surface = whit150,
    onPrimary = white,
    onSecondary = gray,
    onBackground = white,
    onSurface = whit850,
)

private val LightColorPalette = lightColors(
    primary = pink100,
    primaryVariant = purple700,
    secondary = pink900,
    background = white,
    surface = whit850,
    onPrimary = gray,
    onSecondary = white,
    onBackground = gray,
    onSurface = gray,
)

@Composable
fun PlayAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content,
    )
}
