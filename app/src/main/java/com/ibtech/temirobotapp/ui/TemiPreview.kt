package com.ibtech.temirobotapp.ui

/**
 * 실제 Temi 기기 화면 규격. @Preview(device = TEMI_PREVIEW_DEVICE) 로 사용한다.
 *
 * 13.3인치 Temi 화면은 1920x1080px이고 밀도는 약 180dpi다(CommonTopBar의 상태바 실측 기준과 동일).
 * 기본 휴대폰 미리보기로 확인하면 실제 기기보다 훨씬 좁게 보여 여백·글자 크기 판단이 어긋난다.
 */
const val TEMI_PREVIEW_DEVICE = "spec:width=1920px,height=1080px,dpi=180"
