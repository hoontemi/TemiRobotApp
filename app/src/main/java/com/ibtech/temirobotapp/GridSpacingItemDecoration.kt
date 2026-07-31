package com.ibtech.temirobotapp

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 그리드 카드 사이에 가로·세로 동일 간격을 주는 ItemDecoration.
 * 마지막 줄에 카드가 1~2개만 있어도 열 너비는 다른 카드와 동일하게 유지된다
 * (GridLayoutManager 가 각 열 폭을 균등 분할하므로).
 */
class GridSpacingItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager as? GridLayoutManager ?: return
        val spanCount = layoutManager.spanCount
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val column = position % spanCount

        // 카드 사이 간격이 좌우로 균등하게 분배되도록 계산한다.
        outRect.left = column * spacing / spanCount
        outRect.right = spacing - (column + 1) * spacing / spanCount
        outRect.top = if (position < spanCount) 0 else spacing
    }
}
