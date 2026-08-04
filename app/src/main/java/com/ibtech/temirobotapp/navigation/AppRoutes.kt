package com.ibtech.temirobotapp.navigation

/**
 * 화면 이동에 사용하는 Route 문자열 정의.
 * SCREEN_FLOW.md의 Navigation Route 목록과 이름을 맞춘다.
 * 화면이 실제로 구현되는 시점에 맞춰 Route를 추가한다.
 */
object AppRoutes {
    const val HOME = "main"
    const val ADMIN = "admin"
    const val FACILITY_LIST = "facility/list"
    const val FACILITY_DETAIL = "facility/detail"
}
