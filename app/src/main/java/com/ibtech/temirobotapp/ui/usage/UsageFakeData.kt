package com.ibtech.temirobotapp.ui.usage

/**
 * UI 테스트용 샘플 데이터. 실제 이용방법 안내 데이터가 아니며,
 * 화면 이동 확인이 끝나면 관리자 설정/Repository 기반 데이터로 대체될 예정이다.
 */
data class FakeUsageSubItem(
    val id: String,
    val title: String,
    val icon: String
)

data class FakeUsageCategory(
    val id: String,
    val title: String,
    val icon: String,
    val subItems: List<FakeUsageSubItem>
)

val fakeUsageCategories = listOf(
    FakeUsageCategory(
        id = "borrow-return",
        title = "책 대출·반납",
        icon = "📚",
        subItems = listOf(
            FakeUsageSubItem("borrow-limit", "대출 가능 권수", "📋"),
            FakeUsageSubItem("borrow-period", "대출 기간", "📅"),
            FakeUsageSubItem("extend-reserve", "연장·예약", "🔄"),
            FakeUsageSubItem("self-return", "무인반납 방법", "🔒")
        )
    ),
    FakeUsageCategory(
        id = "membership",
        title = "회원가입",
        icon = "🪪",
        subItems = listOf(
            FakeUsageSubItem("target", "가입 대상", "🙋"),
            FakeUsageSubItem("how-to", "가입 방법", "📝"),
            FakeUsageSubItem("documents", "필요 서류", "📄"),
            FakeUsageSubItem("benefits", "회원 혜택", "🎁")
        )
    ),
    FakeUsageCategory(
        id = "reading-room",
        title = "열람실 이용",
        icon = "📖",
        subItems = listOf(
            FakeUsageSubItem("hours", "이용 시간", "⏰"),
            FakeUsageSubItem("seat", "좌석 예약", "💺"),
            FakeUsageSubItem("rules", "이용 수칙", "📏"),
            FakeUsageSubItem("notice", "주의사항", "⚠️")
        )
    ),
    FakeUsageCategory(
        id = "hours",
        title = "운영시간",
        icon = "🕒",
        subItems = listOf(
            FakeUsageSubItem("weekday", "평일 운영시간", "🗓️"),
            FakeUsageSubItem("weekend", "주말 운영시간", "📆"),
            FakeUsageSubItem("closed", "휴관일", "🚫"),
            FakeUsageSubItem("holiday", "공휴일 안내", "🎌")
        )
    )
)
