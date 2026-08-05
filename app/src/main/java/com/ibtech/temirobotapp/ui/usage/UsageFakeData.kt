package com.ibtech.temirobotapp.ui.usage

/**
 * 이용방법 안내 UI 테스트용 샘플 데이터.
 *
 * 여기 담긴 권수·기간·시간 등의 수치는 특정 도서관의 확정 운영정보가 아니라
 * 일반적인 도서관 안내를 가정한 예시 값이다. 이후 관리자 화면에서 모든 문구와
 * 수치를 수정할 수 있도록, 화면 코드에는 문구를 두지 않고 이 파일의 데이터로만 관리한다.
 *
 * 관련 시설/POI 연결 규칙:
 * - [FakeUsageGuide.relatedFacilityId] 는 현재 시설 데이터(FakeFacilityRepository)에
 *   실제로 존재하는 시설만 연결한다. 없으면 null 이고 "관련 시설 안내" 버튼은 숨긴다.
 * - [FakeUsageGuide.relatedPoiName] 은 지도에 등록될 POI 이름을 문자열로 남겨 둔다.
 *   시설로 등록되지 않은 무인반납함·좌석 발급기도 여기에는 기록해 두고,
 *   이후 실제 POI 조회가 붙으면 이 이름으로 목적지를 찾는다.
 */

data class FakeUsageCategory(
    val id: String,
    val title: String,
    val icon: String,
    val description: String,
    val displayOrder: Int,
    val isActive: Boolean
)

data class FakeUsageGuide(
    val id: String,
    val categoryId: String,
    val title: String,
    val icon: String,
    val summary: String,
    val content: String,
    val ttsText: String,
    val relatedFacilityId: String?,
    val relatedPoiName: String?,
    val displayOrder: Int,
    val isActive: Boolean
)

val fakeUsageCategories = listOf(
    FakeUsageCategory(
        id = "borrow-return",
        title = "책 대출·반납",
        icon = "📚",
        description = "도서의 대출, 반납, 연장과 예약 방법을 안내해 드립니다.",
        displayOrder = 1,
        isActive = true
    ),
    FakeUsageCategory(
        id = "membership",
        title = "회원가입",
        icon = "🪪",
        description = "도서관 회원가입 절차와 이용 혜택을 안내해 드립니다.",
        displayOrder = 2,
        isActive = true
    ),
    FakeUsageCategory(
        id = "reading-room",
        title = "열람실 이용",
        icon = "📖",
        description = "열람실 이용시간, 좌석 예약과 이용수칙을 안내해 드립니다.",
        displayOrder = 3,
        isActive = true
    ),
    FakeUsageCategory(
        id = "hours",
        title = "운영시간",
        icon = "🕒",
        description = "도서관 운영시간과 휴관일을 안내해 드립니다.",
        displayOrder = 4,
        isActive = true
    )
)

val fakeUsageGuides = listOf(
    // 1. 책 대출·반납
    FakeUsageGuide(
        id = "borrow-limit",
        categoryId = "borrow-return",
        title = "대출 가능 권수",
        icon = "📋",
        summary = "샘플 기준으로 1인당 최대 5권까지 대출할 수 있습니다.",
        content = """
            현재 화면에는 일반적인 도서관 안내를 위한 샘플 기준으로
            1인당 최대 5권까지 대출할 수 있다고 표시합니다.

            자료 종류와 회원 구분에 따라 대출 가능 권수가 다를 수 있습니다.
            실제 대출 가능 권수는 해당 도서관의 운영 기준을 확인해주세요.
        """.trimIndent(),
        ttsText = "샘플 기준으로 1인당 최대 5권까지 대출할 수 있습니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 1,
        isActive = true
    ),
    FakeUsageGuide(
        id = "borrow-period",
        categoryId = "borrow-return",
        title = "대출 기간",
        icon = "📅",
        summary = "샘플 기준으로 도서 대출 기간은 14일입니다.",
        content = """
            현재 화면에는 일반적인 안내용 샘플 기준으로
            도서 대출 기간을 대출일로부터 14일로 표시합니다.

            자료 종류와 도서관 운영정책에 따라 대출 기간이 달라질 수 있습니다.
            반납 예정일은 대출 확인증이나 도서관 홈페이지에서 확인해주세요.
        """.trimIndent(),
        ttsText = "샘플 기준으로 도서 대출 기간은 14일입니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 2,
        isActive = true
    ),
    FakeUsageGuide(
        id = "borrow-extend",
        categoryId = "borrow-return",
        title = "연장·예약",
        icon = "🔄",
        summary = "예약자가 없는 도서는 대출 기간을 연장할 수 있습니다.",
        content = """
            다른 이용자의 예약이 없는 도서는 대출 기간을 연장할 수 있습니다.
            연장 가능 횟수와 연장 기간은 도서관 운영정책에 따라 달라질 수 있습니다.

            대출 중인 도서는 도서관 홈페이지나 앱에서 예약할 수 있습니다.
            예약 도서가 반납되면 안내된 기간 안에 방문하여 대출해주세요.
        """.trimIndent(),
        ttsText = "예약자가 없는 도서는 대출 기간을 연장할 수 있습니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 3,
        isActive = true
    ),
    FakeUsageGuide(
        id = "borrow-self-return",
        categoryId = "borrow-return",
        title = "무인반납 방법",
        icon = "🔒",
        summary = "운영시간 이후에는 무인반납함을 이용할 수 있습니다.",
        content = """
            대출한 도서는 무인반납기 또는 무인반납함을 통해 반납할 수 있습니다.

            자동 인식 방식은 도서를 한 권씩 넣은 뒤
            반납 완료 화면을 확인해주세요.

            단순 투입형 반납함은 직원 확인 후 최종 반납 처리될 수 있습니다.
            부록이 있는 자료, 전자기기, 대형 자료 등은
            안내데스크에 직접 반납해주세요.
        """.trimIndent(),
        ttsText = "운영시간 이후에는 무인반납함을 이용할 수 있습니다.",
        relatedFacilityId = "info-desk",
        relatedPoiName = "무인반납함",
        displayOrder = 4,
        isActive = true
    ),

    // 2. 회원가입
    FakeUsageGuide(
        id = "membership-target",
        categoryId = "membership",
        title = "가입 대상",
        icon = "🙋",
        summary = "도서관 이용을 원하는 사람은 회원으로 가입할 수 있습니다.",
        content = """
            도서관 이용을 원하는 사람은 도서관 운영 기준에 따라
            회원으로 가입할 수 있습니다.

            거주지역, 연령 또는 재직·재학 여부에 따라
            가입 조건이 달라질 수 있으므로
            세부 자격은 안내데스크에서 확인해주세요.
        """.trimIndent(),
        ttsText = "도서관 이용을 원하는 사람은 회원으로 가입할 수 있습니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 1,
        isActive = true
    ),
    FakeUsageGuide(
        id = "membership-how-to",
        categoryId = "membership",
        title = "가입 방법",
        icon = "📝",
        summary = "온라인 가입 또는 안내데스크 방문으로 가입할 수 있습니다.",
        content = """
            도서관 홈페이지에서 온라인 회원가입을 진행하거나
            안내데스크에서 회원가입 신청서를 작성할 수 있습니다.

            온라인 가입을 완료한 경우에도
            본인 확인과 회원증 발급을 위해 안내데스크 방문이 필요할 수 있습니다.
        """.trimIndent(),
        ttsText = "온라인 가입 또는 안내데스크 방문으로 가입할 수 있습니다.",
        relatedFacilityId = "info-desk",
        relatedPoiName = "안내데스크",
        displayOrder = 2,
        isActive = true
    ),
    FakeUsageGuide(
        id = "membership-documents",
        categoryId = "membership",
        title = "필요 서류",
        icon = "📄",
        summary = "본인 확인을 위한 신분증 또는 관련 서류가 필요합니다.",
        content = """
            성인은 주민등록증, 운전면허증 등 본인 확인이 가능한 신분증이 필요할 수 있습니다.

            학생은 학생증 또는 재학 확인 서류,
            어린이는 보호자 동의와 가족관계 확인 서류가 필요할 수 있습니다.

            필요한 서류는 도서관별로 다를 수 있으므로
            방문 전에 확인해주세요.
        """.trimIndent(),
        ttsText = "본인 확인을 위한 신분증 또는 관련 서류가 필요합니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 3,
        isActive = true
    ),
    FakeUsageGuide(
        id = "membership-benefits",
        categoryId = "membership",
        title = "회원 혜택",
        icon = "🎁",
        summary = "회원은 도서 대출, 예약, 열람실 등 다양한 서비스를 이용할 수 있습니다.",
        content = """
            도서관 회원은 도서와 자료를 대출하고,
            대출 중인 자료를 예약하거나 대출 기간을 연장할 수 있습니다.

            도서관에 따라 열람실 좌석 예약,
            문화 프로그램 신청,
            전자책과 온라인 콘텐츠 등의 서비스를 이용할 수 있습니다.
        """.trimIndent(),
        ttsText = "회원은 도서 대출, 예약, 열람실 등 다양한 서비스를 이용할 수 있습니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 4,
        isActive = true
    ),

    // 3. 열람실 이용
    FakeUsageGuide(
        id = "reading-hours",
        categoryId = "reading-room",
        title = "이용 시간",
        icon = "⏰",
        summary = "샘플 기준으로 열람실은 오전 9시부터 오후 10시까지 운영합니다.",
        content = """
            현재 화면에는 일반적인 안내용 샘플 기준으로
            열람실 운영시간을 오전 9시부터 오후 10시까지 표시합니다.

            도서관 운영일과 시설별 사정에 따라 이용시간이 변경될 수 있으므로
            실제 이용시간은 도서관 공지사항을 확인해주세요.
        """.trimIndent(),
        ttsText = "샘플 기준으로 열람실은 오전 9시부터 오후 10시까지 운영합니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 1,
        isActive = true
    ),
    FakeUsageGuide(
        id = "reading-seat",
        categoryId = "reading-room",
        title = "좌석 예약",
        icon = "💺",
        summary = "좌석 발급기 또는 도서관 앱을 통해 좌석을 예약할 수 있습니다.",
        content = """
            좌석 예약제를 운영하는 열람실은
            좌석 발급기, 도서관 홈페이지 또는 앱에서 좌석을 선택할 수 있습니다.

            배정받은 좌석은 정해진 시간 안에 이용을 시작해야 하며,
            장시간 자리를 비우면 자동으로 반납될 수 있습니다.
        """.trimIndent(),
        ttsText = "좌석 발급기 또는 도서관 앱을 통해 좌석을 예약할 수 있습니다.",
        relatedFacilityId = "reading-room",
        relatedPoiName = "좌석 발급기",
        displayOrder = 2,
        isActive = true
    ),
    FakeUsageGuide(
        id = "reading-rules",
        categoryId = "reading-room",
        title = "이용 수칙",
        icon = "📏",
        summary = "다른 이용자를 위해 조용하고 깨끗하게 이용해주세요.",
        content = """
            열람실에서는 휴대전화를 무음으로 설정하고
            큰 소리의 대화와 통화를 자제해주세요.

            좌석과 주변을 깨끗하게 이용하고,
            다른 이용자에게 방해가 되지 않도록 배려해주세요.
        """.trimIndent(),
        ttsText = "다른 이용자를 위해 조용하고 깨끗하게 이용해주세요.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 3,
        isActive = true
    ),
    FakeUsageGuide(
        id = "reading-notice",
        categoryId = "reading-room",
        title = "주의사항",
        icon = "⚠️",
        summary = "장시간 자리 비움과 음식물 반입 등에 주의해주세요.",
        content = """
            개인 물품만 두고 장시간 자리를 비우지 마세요.
            도서관 규정에 따라 일정 시간 이상 자리를 비우면
            좌석이 자동으로 반납될 수 있습니다.

            음식물 반입, 개인 전열기구 사용,
            좌석 임의 변경 등은 제한될 수 있습니다.
            귀중품은 본인이 직접 관리해주세요.
        """.trimIndent(),
        ttsText = "장시간 자리 비움과 음식물 반입 등에 주의해주세요.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 4,
        isActive = true
    ),

    // 4. 운영시간
    FakeUsageGuide(
        id = "hours-weekday",
        categoryId = "hours",
        title = "평일 운영시간",
        icon = "🗓️",
        summary = "샘플 기준으로 평일은 오전 9시부터 오후 10시까지 운영합니다.",
        content = """
            현재 화면에는 일반적인 안내용 샘플 기준으로
            평일 운영시간을 오전 9시부터 오후 10시까지 표시합니다.

            자료실과 열람실 등 시설별 운영시간은 다를 수 있습니다.
            실제 운영시간은 해당 도서관 공지사항을 확인해주세요.
        """.trimIndent(),
        ttsText = "샘플 기준으로 평일은 오전 9시부터 오후 10시까지 운영합니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 1,
        isActive = true
    ),
    FakeUsageGuide(
        id = "hours-weekend",
        categoryId = "hours",
        title = "주말 운영시간",
        icon = "📆",
        summary = "샘플 기준으로 주말은 오전 9시부터 오후 6시까지 운영합니다.",
        content = """
            현재 화면에는 일반적인 안내용 샘플 기준으로
            토요일과 일요일 운영시간을 오전 9시부터 오후 6시까지 표시합니다.

            주말에는 일부 자료실이나 서비스 운영시간이 단축될 수 있습니다.
        """.trimIndent(),
        ttsText = "샘플 기준으로 주말은 오전 9시부터 오후 6시까지 운영합니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 2,
        isActive = true
    ),
    FakeUsageGuide(
        id = "hours-closed",
        categoryId = "hours",
        title = "휴관일",
        icon = "🚫",
        summary = "정기 휴관일과 도서관 지정 휴관일에는 운영하지 않습니다.",
        content = """
            현재 화면에는 일반적인 샘플 기준으로
            매주 월요일을 정기 휴관일로 표시합니다.

            도서관별 정기 휴관일은 다를 수 있으며,
            시설 점검이나 내부 사정에 따라 임시 휴관할 수 있습니다.
        """.trimIndent(),
        ttsText = "정기 휴관일과 도서관 지정 휴관일에는 운영하지 않습니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 3,
        isActive = true
    ),
    FakeUsageGuide(
        id = "hours-holiday",
        categoryId = "hours",
        title = "공휴일 안내",
        icon = "🎌",
        summary = "법정공휴일에는 휴관하거나 운영시간이 변경될 수 있습니다.",
        content = """
            법정공휴일에는 도서관이 휴관하거나
            평소보다 단축 운영될 수 있습니다.

            설날과 추석 연휴, 선거일 등 특정 공휴일의 운영 여부는
            도서관 공지사항을 확인해주세요.
        """.trimIndent(),
        ttsText = "법정공휴일에는 휴관하거나 운영시간이 변경될 수 있습니다.",
        relatedFacilityId = null,
        relatedPoiName = null,
        displayOrder = 4,
        isActive = true
    )
)

/** 활성화된 카테고리를 표시 순서대로 반환한다. */
fun getUsageCategories(): List<FakeUsageCategory> =
    fakeUsageCategories.filter { it.isActive }.sortedBy { it.displayOrder }

/** ID로 카테고리를 조회한다. 없으면 null. */
fun getUsageCategory(categoryId: String): FakeUsageCategory? =
    fakeUsageCategories.find { it.id == categoryId }

/** 해당 카테고리의 활성화된 안내 항목을 표시 순서대로 반환한다. */
fun getUsageGuides(categoryId: String): List<FakeUsageGuide> =
    fakeUsageGuides.filter { it.categoryId == categoryId && it.isActive }
        .sortedBy { it.displayOrder }

/** ID로 안내 항목을 조회한다. 없으면 null. */
fun getUsageGuide(guideId: String): FakeUsageGuide? =
    fakeUsageGuides.find { it.id == guideId }
