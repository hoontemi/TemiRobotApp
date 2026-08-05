package com.ibtech.temirobotapp.data.facility

/**
 * 시설 안내 UI 테스트용 Fake Repository.
 *
 * 여기서 제공하는 데이터는 실제 도서관 시설 정보가 아니라 화면 구조·데이터 흐름 확인을
 * 위한 샘플이며("(샘플)" 표기 없이도 전부 예시 값이다), 이후 실제 저장소 또는 관리자
 * 설정 기반 Repository 구현으로 대체될 예정이다. 화면 코드는 이 객체를 통해서만
 * 시설 데이터를 조회한다.
 */
object FakeFacilityRepository {

    private val facilities = listOf(
        Facility(
            id = "children-room",
            name = "어린이자료실",
            category = "자료실",
            floor = "2층",
            description = "그림책과 어린이 도서를 모아둔 공간입니다. (샘플 설명 문구)",
            icon = "📚",
            poiName = "어린이자료실",
            escortAvailable = true,
            locationGuideText = "2층 계단에서 왼쪽으로 이동하면 어린이자료실이 보입니다. (샘플 위치 안내)",
            ttsText = "어린이자료실은 2층에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 1
        ),
        Facility(
            id = "general-room",
            name = "종합자료실",
            category = "자료실",
            floor = "3층",
            description = "다양한 분야의 일반 도서를 열람할 수 있는 공간입니다. (샘플 설명 문구)",
            icon = "📖",
            poiName = "종합자료실",
            escortAvailable = true,
            locationGuideText = "3층 엘리베이터 맞은편에 종합자료실 입구가 있습니다. (샘플 위치 안내)",
            ttsText = "종합자료실은 3층에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 2
        ),
        Facility(
            id = "digital-room",
            name = "디지털자료실",
            category = "자료실",
            floor = "1층",
            description = "전자책과 멀티미디어 자료를 이용할 수 있는 공간입니다. (샘플 설명 문구)",
            icon = "💻",
            poiName = "디지털자료실",
            escortAvailable = true,
            locationGuideText = "1층 안내데스크 안쪽으로 디지털자료실이 있습니다. (샘플 위치 안내)",
            ttsText = "디지털자료실은 1층에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 3
        ),
        Facility(
            id = "reading-room",
            name = "열람실",
            category = "학습공간",
            floor = "3층",
            description = "조용히 책을 읽거나 공부할 수 있는 공간입니다. (샘플 설명 문구)",
            icon = "📝",
            poiName = "열람실",
            escortAvailable = true,
            locationGuideText = "3층 종합자료실 옆에 열람실이 있습니다. (샘플 위치 안내)",
            ttsText = "열람실은 3층에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 4
        ),
        Facility(
            id = "info-desk",
            name = "안내데스크",
            category = "편의시설",
            floor = "1층",
            description = "도서관 이용 문의를 도와드리는 곳입니다. (샘플 설명 문구)",
            icon = "🛎️",
            poiName = "안내데스크",
            escortAvailable = true,
            locationGuideText = "1층 정문 입구 바로 앞에 안내데스크가 있습니다. (샘플 위치 안내)",
            ttsText = "안내데스크는 1층 정문 앞에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 5
        ),
        Facility(
            id = "restroom",
            name = "화장실",
            category = "편의시설",
            floor = "1층",
            description = "화장실 위치 안내입니다. (샘플 설명 문구)",
            icon = "🚻",
            poiName = null,
            escortAvailable = false,
            locationGuideText = "1층 엘리베이터 옆에 화장실이 있습니다. (샘플 위치 안내)",
            ttsText = "화장실은 1층 엘리베이터 옆에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 6
        ),
        Facility(
            id = "elevator",
            name = "엘리베이터",
            category = "이동수단",
            floor = "전층",
            description = "각 층으로 이동할 수 있는 엘리베이터입니다. (샘플 설명 문구)",
            icon = "🛗",
            poiName = "엘리베이터",
            escortAvailable = false,
            locationGuideText = "로비 중앙에 엘리베이터가 있습니다. (샘플 위치 안내)",
            ttsText = "엘리베이터는 로비 중앙에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 7
        ),
        Facility(
            id = "stairs",
            name = "계단",
            category = "이동수단",
            floor = "전층",
            description = "각 층으로 이동할 수 있는 계단입니다. (샘플 설명 문구)",
            icon = "🪜",
            poiName = null,
            escortAvailable = false,
            locationGuideText = "엘리베이터 옆 통로를 따라가면 계단이 있습니다. (샘플 위치 안내)",
            ttsText = "계단은 엘리베이터 옆에 있습니다. (샘플 음성 안내 문구)",
            image = null,
            isActive = true,
            displayOrder = 8
        )
    )

    /** 활성화된 시설을 표시 순서대로 반환한다. */
    fun getFacilities(): List<Facility> =
        facilities.filter { it.isActive }.sortedBy { it.displayOrder }

    /**
     * 시설 안내 첫 화면에 큰 버튼으로 노출할 주요 장소를 반환한다.
     * 표시 순서가 빠른 [count]개를 주요 장소로 본다. 나머지는 "다른 장소 찾기"에서 전체 목록으로 보여준다.
     * (이후 관리자 화면에서 주요 장소를 직접 지정할 수 있게 되면 그 설정을 따르도록 바꾼다.)
     */
    fun getPrimaryFacilities(count: Int = PRIMARY_FACILITY_COUNT): List<Facility> =
        getFacilities().take(count)

    /** ID로 시설을 조회한다. 없으면 null. */
    fun getFacilityById(id: String): Facility? =
        facilities.find { it.id == id }

    /** 활성화된 시설의 카테고리 목록을 중복 없이 반환한다. */
    fun getCategories(): List<String> =
        getFacilities().map { it.category }.distinct()

    /** 첫 화면에 큰 버튼으로 보여줄 주요 장소 개수(2x2 그리드). */
    private const val PRIMARY_FACILITY_COUNT = 4
}
