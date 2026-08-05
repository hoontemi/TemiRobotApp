package com.ibtech.temirobotapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * 13.3인치 가로형 Temi 화면 기준 Typography 구조.
 * 화면과 멀리 떨어져서도 읽을 수 있도록 기본 Material3 크기보다 크게 잡았다.
 * 세부 폰트(자체 서체 등) 디자인은 이후 단계에서 조정한다.
 */
val TemiTypography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp
    )
)
