package com.ibtech.temirobotapp.ui.usage

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibtech.temirobotapp.ui.TEMI_PREVIEW_DEVICE
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import com.ibtech.temirobotapp.ui.theme.TemiRobotAppTheme

/**
 * 이용방법 카테고리 화면. 카테고리 카드가 2x2로 화면의 남는 공간을 모두 채운다.
 * 현재는 FakeUsageCategory 샘플 데이터를 사용한다.
 */
@Composable
fun UsageCategoryScreen(
    onCategoryClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val categories = getUsageCategories()

    Scaffold(
        topBar = {
            CommonTopBar(title = "이용방법 안내", onBackClick = onBackClick, onHomeClick = onHomeClick)
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
                    .padding(horizontal = 48.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "무엇이 궁금하신가요?",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(28.dp))

                // 4개 고정이라 스크롤이 필요 없다. weight로 남는 높이를 균등하게 나눈다.
                categories.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        rowItems.forEach { category ->
                            UsageCategoryCard(
                                category = category,
                                onClick = { onCategoryClick(category.id) },
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
private fun UsageCategoryCard(
    category: FakeUsageCategory,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = category.icon, fontSize = 64.sp)
            Spacer(modifier = Modifier.width(28.dp))
            Text(
                text = category.title,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

/** 실제 Temi 화면 규격(1920x1080px, 밀도 180)으로 미리보기한다. */
@Preview(showBackground = true, device = TEMI_PREVIEW_DEVICE)
@Composable
private fun UsageCategoryScreenPreview() {
    TemiRobotAppTheme {
        UsageCategoryScreen(onCategoryClick = {}, onBackClick = {}, onHomeClick = {})
    }
}
