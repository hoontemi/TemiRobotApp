package com.ibtech.temirobotapp.ui.event

/**
 * UI 테스트용 샘플 데이터. 실제 행사 데이터가 아니며,
 * 화면 이동 확인이 끝나면 관리자 설정/Repository 기반 데이터로 대체될 예정이다.
 */
data class FakeEvent(
    val id: String,
    val name: String,
    val date: String,
    val time: String,
    val place: String,
    val target: String,
    val description: String
)

val fakeEvents = listOf(
    FakeEvent(
        id = "event-1",
        name = "여름 독서교실 (샘플)",
        date = "2026-08-10",
        time = "14:00 ~ 15:30",
        place = "2층 세미나실",
        target = "초등학생",
        description = "여름방학 독서 프로그램 샘플 설명 문구입니다."
    ),
    FakeEvent(
        id = "event-2",
        name = "북토크: 작가와의 만남 (샘플)",
        date = "2026-08-15",
        time = "16:00 ~ 17:00",
        place = "1층 로비",
        target = "전체",
        description = "작가 초청 강연 샘플 설명 문구입니다."
    ),
    FakeEvent(
        id = "event-3",
        name = "도서관 예절 교육 (샘플)",
        date = "2026-08-20",
        time = "11:00 ~ 11:30",
        place = "어린이자료실",
        target = "유아·초등 저학년",
        description = "어린이 대상 도서관 예절 교육 샘플 설명 문구입니다."
    )
)
