package com.ibtech.temirobotapp.ui.facility

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 시설 상세 화면 자리표시자.
 * HomeScreen과 동일한 중앙 정렬·최대 폭 제한 원칙만 적용했으며,
 * 실제 시설 상세 콘텐츠는 이후 단계에서 구현한다.
 */
@Composable
fun FacilityDetailScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.widthIn(max = 480.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "시설 상세", style = MaterialTheme.typography.headlineLarge)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
private fun FacilityDetailScreenPreview() {
    FacilityDetailScreen()
}
