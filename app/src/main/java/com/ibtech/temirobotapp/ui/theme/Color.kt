package com.ibtech.temirobotapp.ui.theme

import androidx.compose.ui.graphics.Color

// REQUIREMENTS.md 17장 디자인 요구사항: 화이트 배경 + 민트/청록 강조색
val TemiMint = Color(0xFF48CDC5)
val TemiMintDark = Color(0xFF2FA39C)
val TemiBackground = Color(0xFFF5F5F5)
val TemiSurface = Color(0xFFFFFFFF)
val TemiOnPrimary = Color(0xFFFFFFFF)
val TemiOnSurface = Color(0xFF1C1B1F)

// 컨테이너/외곽선 계열.
// 이 값들을 지정하지 않으면 Material 3 기본 팔레트(보라 계열)가 그대로 쓰여
// 카드와 칩이 민트 테마와 어긋난 색으로 표시된다.
/** 카드·큰 버튼 배경으로 쓰는 연한 민트. */
val TemiMintContainer = Color(0xFFCCF0EC)
/** 연한 민트 위에 올리는 글자·아이콘 색. */
val TemiOnMintContainer = Color(0xFF0A4A45)
/** 배지 등 강조 요소 배경(진한 청록). */
val TemiTealContainer = Color(0xFFDCF2F0)
val TemiOnTealContainer = Color(0xFF0A4A45)
/** 카드 테두리. */
val TemiOutline = Color(0xFF9EC4C0)
val TemiOutlineVariant = Color(0xFFD3E6E4)
/** 보조 문구(설명·요약) 색. */
val TemiSurfaceVariant = Color(0xFFE6EFEE)
val TemiOnSurfaceVariant = Color(0xFF445755)
