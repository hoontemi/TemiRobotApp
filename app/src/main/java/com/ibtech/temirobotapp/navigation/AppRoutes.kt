package com.ibtech.temirobotapp.navigation

/**
 * 화면 이동에 사용하는 Route 문자열 정의.
 * SCREEN_FLOW.md의 Navigation Route 목록과 이름을 맞춘다.
 * 화면이 실제로 구현되는 시점에 맞춰 Route를 추가한다.
 */
object AppRoutes {
    const val HOME = "main"
    const val ADMIN = "admin"

    const val ARG_FACILITY_ID = "facilityId"
    const val FACILITY_LIST = "facility/list"
    const val FACILITY_DETAIL = "facility/detail/{$ARG_FACILITY_ID}"
    fun facilityDetail(facilityId: String) = "facility/detail/$facilityId"

    const val ARG_CATEGORY_ID = "categoryId"
    const val USAGE_CATEGORY = "usage/category"
    const val USAGE_DETAIL = "usage/detail/{$ARG_CATEGORY_ID}"
    fun usageDetail(categoryId: String) = "usage/detail/$categoryId"

    const val CHILDREN_MENU = "children/menu"

    const val ARG_EVENT_ID = "eventId"
    const val EVENT_LIST = "event/list"
    const val EVENT_DETAIL = "event/detail/{$ARG_EVENT_ID}"
    fun eventDetail(eventId: String) = "event/detail/$eventId"
}
