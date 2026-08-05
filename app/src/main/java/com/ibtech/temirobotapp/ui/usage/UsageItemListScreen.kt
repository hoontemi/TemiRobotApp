package com.ibtech.temirobotapp.ui.usage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibtech.temirobotapp.ui.TEMI_PREVIEW_DEVICE
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import com.ibtech.temirobotapp.ui.theme.TemiRobotAppTheme

/**
 * 이용방법 세부 항목 목록 화면. 카테고리 설명 아래에 안내 항목을 제목·요약과 함께
 * 2x2 카드로 보여준다. 카드가 남는 높이를 모두 나눠 가져 빈 공간을 남기지 않는다.
 * 항목을 선택하면 세부 안내 화면으로 이동한다.
 */
@Composable
fun UsageItemListScreen(
    categoryId: String,
    onGuideClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val category = getUsageCategory(categoryId)
    val guides = getUsageGuides(categoryId)

    Scaffold(
        topBar = {
            CommonTopBar(
                title = category?.title ?: "이용방법 안내",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 48.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = category?.description.orEmpty(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                guides.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(28.dp)
                    ) {
                        rowItems.forEach { guide ->
                            UsageGuideCard(
                                guide = guide,
                                onClick = { onGuideClick(guide.id) },
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UsageGuideCard(
    guide: FakeUsageGuide,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = guide.icon, fontSize = 44.sp)
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = guide.title,
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = guide.summary,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/** 요약 문구가 가장 긴 "회원가입" 기준으로 잘림 여부까지 확인한다. */
@Preview(showBackground = true, device = TEMI_PREVIEW_DEVICE)
@Composable
private fun UsageItemListScreenPreview() {
    TemiRobotAppTheme {
        UsageItemListScreen(
            categoryId = "membership",
            onGuideClick = {},
            onBackClick = {},
            onHomeClick = {}
        )
    }
}
