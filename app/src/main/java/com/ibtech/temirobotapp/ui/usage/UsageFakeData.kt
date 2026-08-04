package com.ibtech.temirobotapp.ui.usage

/**
 * UI 테스트용 샘플 데이터. 실제 이용방법 안내 데이터가 아니며,
 * 화면 이동 확인이 끝나면 관리자 설정/Repository 기반 데이터로 대체될 예정이다.
 */
data class FakeUsageCategory(
    val id: String,
    val title: String,
    val guide: String
)

val fakeUsageCategories = listOf(
    FakeUsageCategory(
        id = "borrow-return",
        title = "책 대출·반납",
        guide = "대출과 반납 방법에 대한 샘플 안내 문구입니다."
    ),
    FakeUsageCategory(
        id = "membership",
        title = "회원가입",
        guide = "회원가입 절차에 대한 샘플 안내 문구입니다."
    ),
    FakeUsageCategory(
        id = "reading-room",
        title = "열람실 이용",
        guide = "열람실 이용 방법에 대한 샘플 안내 문구입니다."
    ),
    FakeUsageCategory(
        id = "hours",
        title = "운영시간·휴관일",
        guide = "운영시간과 휴관일에 대한 샘플 안내 문구입니다."
    ),
    FakeUsageCategory(
        id = "card",
        title = "회원증",
        guide = "회원증 발급·재발급에 대한 샘플 안내 문구입니다."
    ),
    FakeUsageCategory(
        id = "self-return",
        title = "무인반납",
        guide = "무인반납기 이용 방법에 대한 샘플 안내 문구입니다."
    )
)
