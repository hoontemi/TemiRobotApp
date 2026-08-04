package com.ibtech.temirobotapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * 홈 화면을 제외한 모든 하위 화면에서 사용하는 공통 상단바.
 * 왼쪽: 뒤로가기, 가운데: 화면 제목, 오른쪽: 홈 버튼.
 * 아이콘은 최종 디자인 전 자리표시자(텍스트/이모지)다.
 */
@Composable
fun CommonTopBar(
    title: String,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    // Temi 기기의 자체 상태바가 표준 WindowInsets로 보고되지 않아
    // statusBarsPadding()이 동작하지 않는다. 실측값(밀도 180 기준 약 56dp) 기반
    // 고정 여백으로 Temi 상태바와 겹치지 않게 처리한다.
    Surface(shadowElevation = 2.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp)
                .height(64.dp)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBackClick) {
                Text(text = "← 뒤로", style = MaterialTheme.typography.labelLarge)
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            TextButton(onClick = onHomeClick) {
                Text(text = "🏠 홈", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
