package com.ibtech.temirobotapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibtech.temirobotapp.ui.LibraryViewModel
import com.ibtech.temirobotapp.ui.TEMI_PREVIEW_DEVICE
import com.ibtech.temirobotapp.ui.TEMI_STATUS_BAR_HEIGHT
import com.ibtech.temirobotapp.ui.theme.TemiRobotAppTheme

/**
 * 홈 화면. 13.3인치 가로 화면을 그대로 쓰기 위해 최대 너비 제한 없이 전체 폭을 사용한다.
 *
 * 상단에 도서관 이름과 인사말, 가운데에 방문 목적 3가지를 큰 카드로 나란히 두고,
 * 하단에 오늘의 행사와 관리자 진입을 배치한다.
 * 아이콘은 최종 디자인 전 자리표시자(이모지)이며, 도서관 이름은 LibraryViewModel의 저장값을 쓴다.
 *
 * 메인 화면에는 홈 버튼이 필요 없어 CommonTopBar를 쓰지 않는다.
 * 대신 Temi 상태바와 겹치지 않도록 상단 여백을 직접 확보한다.
 */
@Composable
fun HomeScreen(
    libraryViewModel: LibraryViewModel,
    onAdminClick: () -> Unit,
    onFacilityClick: () -> Unit,
    onUsageClick: () -> Unit,
    onChildrenClick: () -> Unit,
    onEventClick: () -> Unit
) {
    val libraryName by libraryViewModel.libraryName.collectAsState()

    HomeContent(
        libraryName = libraryName,
        onAdminClick = onAdminClick,
        onFacilityClick = onFacilityClick,
        onUsageClick = onUsageClick,
        onChildrenClick = onChildrenClick,
        onEventClick = onEventClick
    )
}

/** 도서관 이름만 받아 그리는 화면 본체. ViewModel과 분리해 미리보기에서도 그대로 확인한다. */
@Composable
private fun HomeContent(
    libraryName: String,
    onAdminClick: () -> Unit,
    onFacilityClick: () -> Unit,
    onUsageClick: () -> Unit,
    onChildrenClick: () -> Unit,
    onEventClick: () -> Unit
) {
    val menus = listOf(
        HomeMenu("🔍", "시설을", "찾고 있어요", onFacilityClick),
        HomeMenu("📖", "이용방법이", "궁금해요", onUsageClick),
        HomeMenu("🤖", "로봇과", "놀아요", onChildrenClick)
    )

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = TEMI_STATUS_BAR_HEIGHT)
                .padding(horizontal = 56.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. 상단: 로고 + 도서관 이름 + 인사말
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🌿", fontSize = 48.sp)
                }

                Spacer(modifier = Modifier.width(24.dp))

                Text(
                    text = libraryName,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "안녕하세요, 무엇을 도와드릴까요?",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 2. 가운데: 방문 목적 3가지를 가로로 나란히. 남는 높이를 모두 채운다.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                menus.forEach { menu ->
                    HomeMenuCard(
                        menu = menu,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // 3. 하단: 오늘의 행사 + 관리자
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onEventClick,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(96.dp)
                ) {
                    Text(
                        text = "📅  오늘의 행사",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                OutlinedButton(
                    onClick = onAdminClick,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .width(220.dp)
                        .height(96.dp)
                ) {
                    Text(text = "관리자", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

private data class HomeMenu(
    val emoji: String,
    val firstLine: String,
    val secondLine: String,
    val onClick: () -> Unit
)

/** 방문 목적 카드. 아이콘을 크게 위에 두고 문구를 두 줄로 아래에 둔다. */
@Composable
private fun HomeMenuCard(menu: HomeMenu, modifier: Modifier = Modifier) {
    Surface(
        onClick = menu.onClick,
        shape = RoundedCornerShape(32.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = menu.emoji, fontSize = 96.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "${menu.firstLine}\n${menu.secondLine}",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                lineHeight = 62.sp
            )
        }
    }
}

@Preview(showBackground = true, device = TEMI_PREVIEW_DEVICE)
@Composable
private fun HomeScreenPreview() {
    TemiRobotAppTheme {
        HomeContent(
            libraryName = "테미 도서관",
            onAdminClick = {},
            onFacilityClick = {},
            onUsageClick = {},
            onChildrenClick = {},
            onEventClick = {}
        )
    }
}
