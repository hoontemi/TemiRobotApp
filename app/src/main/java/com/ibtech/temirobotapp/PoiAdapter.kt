package com.ibtech.temirobotapp

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

/**
 * temi 지도의 POI 이름 목록을 Figma 카드 UI로 표시하는 Adapter.
 *
 * 이동에 사용하는 식별자는 오직 [name] 이며, 색상·아이콘은 표시(UI) 전용이라
 * 이동 로직에 관여하지 않는다. 클릭 시 [onPoiClick] 으로 원본 POI 이름을 그대로 전달한다.
 */
class PoiAdapter(
    private val onPoiClick: (String) -> Unit
) : RecyclerView.Adapter<PoiAdapter.PoiViewHolder>() {

    private val items = mutableListOf<String>()

    /** 카드 활성/비활성 상태 (이동 중에는 전체 비활성) */
    private var itemsEnabled: Boolean = true

    fun submit(pois: List<String>) {
        items.clear()
        items.addAll(pois)
        notifyDataSetChanged()
    }

    fun setEnabledState(enabled: Boolean) {
        if (itemsEnabled == enabled) return
        itemsEnabled = enabled
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poi_card, parent, false)
        return PoiViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PoiViewHolder, position: Int) {
        holder.bind(items[position], position, itemsEnabled, onPoiClick)
    }

    class PoiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: MaterialCardView = itemView.findViewById(R.id.poiCard)
        private val cardInner: ConstraintLayout = itemView.findViewById(R.id.poiCardInner)
        private val icon: ImageView = itemView.findViewById(R.id.poiIcon)
        private val title: TextView = itemView.findViewById(R.id.poiTitle)
        private val subtitle: TextView = itemView.findViewById(R.id.poiSubtitle)

        fun bind(
            name: String,
            position: Int,
            enabled: Boolean,
            onPoiClick: (String) -> Unit
        ) {
            val context = itemView.context
            // ViewHolder 재사용으로 이전 상태가 남지 않도록 모든 값을 매 bind 에서 명시적으로 설정한다.
            title.text = name.ifBlank { "이름 없음" }

            // 보조(영문) 설명은 실제 데이터가 없으므로 표시하지 않는다. (임의 생성 금지)
            subtitle.visibility = View.GONE

            icon.setImageResource(getPoiIcon(name))

            // 카드 배경: 메인 화면 버튼의 그라디언트 팔레트(9색)를 인덱스로 순환.
            //   콘텐츠(텍스트·아이콘·화살표)는 메인 화면과 동일하게 흰색.
            //   색상은 표시(UI) 전용이며 이동 식별자(name)에는 영향을 주지 않는다.
            if (enabled) {
                cardInner.setBackgroundResource(getPoiGradient(position))
                title.setTextColor(ContextCompat.getColor(context, R.color.poi_title_on_color))
                icon.setIconTint(context, R.color.poi_icon_on_tint)
            } else {
                // 비활성(이동 중) 상태: 밝은 회색 단색 배경 + 뮤트 처리
                cardInner.setBackgroundResource(R.drawable.poi_grad_disabled)
                title.setTextColor(ContextCompat.getColor(context, R.color.poi_title_on_color_disabled))
                icon.setIconTint(context, R.color.poi_icon_on_tint_disabled)
            }

            card.isEnabled = enabled
            card.isClickable = enabled
            card.alpha = if (enabled) 1f else 0.6f

            // 클릭 리스너는 매 bind 에서 새로 설정 → 중복 등록/오바인딩 방지.
            // 이동 식별자는 항상 원본 POI 이름(name)을 그대로 전달한다.
            card.setOnClickListener {
                if (enabled && name.isNotBlank()) onPoiClick(name)
            }
        }

        private fun ImageView.setIconTint(context: android.content.Context, colorRes: Int) {
            ImageViewCompat.setImageTintList(
                this,
                ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
            )
        }
    }

    companion object {
        /** 메인 화면 버튼 그라디언트 팔레트 (9색 순환). UI 전용. */
        private val CARD_GRADIENTS = intArrayOf(
            R.drawable.poi_grad_1, R.drawable.poi_grad_2, R.drawable.poi_grad_3,
            R.drawable.poi_grad_4, R.drawable.poi_grad_5, R.drawable.poi_grad_6,
            R.drawable.poi_grad_7, R.drawable.poi_grad_8, R.drawable.poi_grad_9
        )

        /** POI 인덱스 → 카드 배경 그라디언트 (9색 순환). UI 전용. */
        fun getPoiGradient(index: Int): Int =
            CARD_GRADIENTS[Math.floorMod(index, CARD_GRADIENTS.size)]

        /**
         * POI 이름 → 아이콘 리소스. UI 전용이며 이동 데이터(name)를 변경하지 않는다.
         * 매핑되지 않으면 항상 기본 place 아이콘을 반환한다.
         * 성별 화장실처럼 더 구체적인 키워드를 먼저 검사한다.
         */
        fun getPoiIcon(rawName: String): Int {
            val name = rawName.lowercase()
            return when {
                // 화장실 (성별 구체 항목 우선)
                name.contains("남자") || name.contains("남성") ||
                    name.contains("men") || name.contains("male") -> R.drawable.ic_poi_man
                name.contains("여자") || name.contains("여성") ||
                    name.contains("women") || name.contains("female") -> R.drawable.ic_poi_woman
                name.contains("화장실") || name.contains("toilet") ||
                    name.contains("restroom") || name.contains("wc") -> R.drawable.ic_poi_wc

                // 이동/통로
                name.contains("엘리베이터") || name.contains("승강기") ||
                    name.contains("elevator") || name.contains("lift") -> R.drawable.ic_poi_elevator
                name.contains("계단") || name.contains("stairs") -> R.drawable.ic_poi_stairs
                name.contains("출입구") || name.contains("입구") || name.contains("출구") ||
                    name.contains("정문") || name.contains("후문") ||
                    name.contains("entrance") || name.contains("exit") ||
                    name.contains("gate") || name.contains("door") -> R.drawable.ic_poi_login

                // 식음료
                name.contains("카페") || name.contains("커피") ||
                    name.contains("cafe") || name.contains("coffee") -> R.drawable.ic_poi_cafe
                name.contains("식당") || name.contains("음식") || name.contains("푸드") ||
                    name.contains("restaurant") || name.contains("dining") -> R.drawable.ic_poi_restaurant

                // 공간
                name.contains("회의") || name.contains("미팅") ||
                    name.contains("meeting") || name.contains("conference") -> R.drawable.ic_poi_meeting_room
                name.contains("휴게") || name.contains("라운지") ||
                    name.contains("lounge") -> R.drawable.ic_poi_chair
                name.contains("강당") || name.contains("홀") || name.contains("세미나") ||
                    name.contains("hall") || name.contains("auditorium") -> R.drawable.ic_poi_groups
                name.contains("사무") || name.contains("office") -> R.drawable.ic_poi_business
                name.contains("도서") || name.contains("서재") ||
                    name.contains("library") || name.contains("book") -> R.drawable.ic_poi_book

                // 기능
                name.contains("충전") || name.contains("charging") ||
                    name.contains("charge") -> R.drawable.ic_poi_charging
                name.contains("접수") || name.contains("reception") ||
                    name.contains("reg") -> R.drawable.ic_poi_assignment
                name.contains("안내") || name.contains("인포") ||
                    name.contains("info") || name.contains("desk") -> R.drawable.ic_poi_info

                // 기타 → 기본 아이콘
                else -> R.drawable.ic_poi_place
            }
        }
    }
}
