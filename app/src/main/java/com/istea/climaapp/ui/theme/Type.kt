package com.istea.climaapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.istea.climaapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Inknut Antiqua"),
        fontProvider = provider
    )
)

// Default Material 3 typography values
val baseline = Typography()

enum class WindowSize { Compact, Medium, Expanded }

@Composable
fun getWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> WindowSize.Compact
        configuration.screenWidthDp < 840 -> WindowSize.Medium
        else -> WindowSize.Expanded
    }
}

@Composable
fun AppTypography(windowSize: WindowSize): Typography {
    return when (windowSize) {
        WindowSize.Compact -> Typography(
            displayLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 50.sp,
                lineHeight = 60.sp
            ),
            displayMedium = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 38.sp,
                lineHeight = 48.sp
            ),
            headlineLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 30.sp,
                lineHeight = 36.sp
            ),
            headlineMedium = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 26.sp,
                lineHeight = 32.sp
            ),
            titleLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 20.sp,
                lineHeight = 24.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 17.sp,
                lineHeight = 22.sp
            )
        )
        WindowSize.Medium -> Typography(
            displayLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 60.sp,
                lineHeight = 72.sp
            ),
            displayMedium = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 45.sp,
                lineHeight = 56.sp
            ),
            headlineLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 38.sp,
                lineHeight = 48.sp
            ),
            headlineMedium = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 30.sp,
                lineHeight = 36.sp
            ),
            titleLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 23.sp,
                lineHeight = 28.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 19.sp,
                lineHeight = 24.sp
            )
        )
        WindowSize.Expanded -> Typography(
            displayLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 68.sp,
                lineHeight = 80.sp
            ),
            displayMedium = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 53.sp,
                lineHeight = 64.sp
            ),
            headlineLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 44.sp,
                lineHeight = 56.sp
            ),
            headlineMedium = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 36.sp,
                lineHeight = 44.sp
            ),
            titleLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 28.sp,
                lineHeight = 36.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = displayFontFamily,
                fontSize = 23.sp,
                lineHeight = 28.sp
            )
        )
    }
}