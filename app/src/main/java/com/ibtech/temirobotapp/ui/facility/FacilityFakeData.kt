package com.ibtech.temirobotapp.ui.facility

/**
 * UI 테스트용 샘플 데이터. 실제 도서관 시설 데이터가 아니며,
 * 화면 이동 확인이 끝나면 관리자 설정/Repository 기반 데이터로 대체될 예정이다.
 */
data class FakeFacility(
    val id: String,
    val name: String,
    val floor: String,
    val description: String
)

val fakeFacilities = listOf(
    FakeFacility(
        id = "children-room",
        name = "어린이자료실",
        floor = "2층",
        description = "그림책과 어린이 도서를 모아둔 공간입니다. (샘플 설명 문구)"
    ),
    FakeFacility(
        id = "general-room",
        name = "종합자료실",
        floor = "3층",
        description = "다양한 분야의 일반 도서를 열람할 수 있는 공간입니다. (샘플 설명 문구)"
    ),
    FakeFacility(
        id = "digital-room",
        name = "디지털자료실",
        floor = "1층",
        description = "전자책과 멀티미디어 자료를 이용할 수 있는 공간입니다. (샘플 설명 문구)"
    ),
    FakeFacility(
        id = "reading-room",
        name = "열람실",
        floor = "3층",
        description = "조용히 책을 읽거나 공부할 수 있는 공간입니다. (샘플 설명 문구)"
    ),
    FakeFacility(
        id = "info-desk",
        name = "안내데스크",
        floor = "1층",
        description = "도서관 이용 문의를 도와드리는 곳입니다. (샘플 설명 문구)"
    ),
    FakeFacility(
        id = "restroom",
        name = "화장실",
        floor = "1층",
        description = "화장실 위치 안내입니다. (샘플 설명 문구)"
    )
)
