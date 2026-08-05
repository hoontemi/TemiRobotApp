package com.ibtech.temirobotapp.ui

import androidx.compose.ui.unit.dp

/**
 * Temi 기기의 시스템 바 실측값.
 *
 * 이 기기는 상단 상태바와 하단 내비게이션 바를 앱 위에 오버레이로 그리면서
 * 표준 WindowInsets로 보고하지 않는다. 그래서 statusBarsPadding()/navigationBarsPadding()이
 * 동작하지 않고, enableEdgeToEdge()를 꺼도 창 크기가 줄지 않는다.
 * (1920x1080 / 밀도 180 기준 스크린샷에서 직접 측정한 값이다.)
 *
 * 기기가 바뀌면 이 두 값만 고치면 된다.
 */

/** 상단 상태바 높이. [com.ibtech.temirobotapp.ui.components.CommonTopBar]에서 사용한다. */
val TEMI_STATUS_BAR_HEIGHT = 56.dp

/**
 * 하단 내비게이션 바(◁ ○ □) 높이. 실측 34px ≈ 30dp에 여유를 조금 더했다.
 * MainActivity에서 앱 전체에 한 번 적용하므로 개별 화면에서 다시 넣지 않는다.
 */
val TEMI_NAVIGATION_BAR_HEIGHT = 36.dp
