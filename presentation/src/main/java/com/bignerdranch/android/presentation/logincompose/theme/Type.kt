package com.bignerdranch.android.presentation.logincompose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.presentation.R

val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
)

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)


val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Roboto"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Roboto"),
        fontProvider = provider,
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily, color = Color.White),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily, color = Color.White),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily, color = Color.White),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily, color = Color.White),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily, color = Color.White),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily, color = Color.White),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily, color = Color.White),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily, color = Color.White),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily, color = Color.White),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily, color = Color.White),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily, color = Color.White),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily, color = Color.White),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily, color = Color.White),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily, color = Color.White),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily, color = Color.White),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)