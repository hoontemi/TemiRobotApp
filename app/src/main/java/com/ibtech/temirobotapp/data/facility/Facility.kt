package com.ibtech.temirobotapp.data.facility

/**
 * 시설 안내 데이터 모델.
 *
 * REQUIREMENTS.md 6.1(시설 목록 화면)에 정의된 시설 데이터 항목을 그대로 반영했으며,
 * 추후 관리자 화면에서 각 필드를 등록·수정할 수 있는 구조를 염두에 두고 설계했다.
 * 현재는 [com.ibtech.temirobotapp.data.facility.FakeFacilityRepository]가 제공하는
 * 샘플 데이터만 사용하며, 실제 저장소(Room 등)나 관리자 편집 기능은 아직 연결하지 않는다.
 */
data class Facility(
    /** 시설 고유 ID. Route 인자 및 목록/상세 화면 매칭에 사용한다. */
    val id: String,
    /** 시설 이름. */
    val name: String,
    /** 카테고리. 시설 목록 화면의 카테고리 필터에 사용한다. */
    val category: String,
    /** 층수 표시 문구 (예: "2층", "전층"). */
    val floor: String,
    /** 시설 상세 화면에 표시할 일반 설명. */
    val description: String,
    /** 목록/상세 화면에 표시할 아이콘. 최종 아이콘 디자인 전 이모지 자리표시자다. */
    val icon: String,
    /** 연결된 Temi POI 이름. 등록되지 않은 시설은 null이다. */
    val poiName: String?,
    /** 동행 안내 가능 여부. false면 시설 상세 화면의 동행 안내 버튼을 비활성화한다. */
    val escortAvailable: Boolean,
    /** 위치만 보기·위치 안내 화면에 표시하는 안내 문구. */
    val locationGuideText: String,
    /** TTS로 재생할 안내 문구. 현재는 실제 TTS를 실행하지 않는다. */
    val ttsText: String,
    /** 대표 이미지 식별자(경로 또는 리소스 키). 이미지가 없으면 null이며 자리표시자를 보여준다. */
    val image: String?,
    /** 활성화 여부. false인 시설은 목록에 노출하지 않는다. */
    val isActive: Boolean,
    /** 목록 표시 순서. 값이 작을수록 먼저 표시한다. */
    val displayOrder: Int
)
