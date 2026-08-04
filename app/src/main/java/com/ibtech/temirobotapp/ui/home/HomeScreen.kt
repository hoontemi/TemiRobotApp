package com.ibtech.temirobotapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ibtech.temirobotapp.ui.LibraryViewModel
import androidx.compose.material3.Text

/**
 * 홈 화면. 첨부 이미지의 레이아웃 구조(상단 로고·이름·인사말, 중앙 큰 버튼 3개,
 * 하단 소형 행사 버튼 + 관리자 버튼, 배경 장식)를 따른다.
 * 아이콘은 최종 디자인 전 자리표시자(이모지)이며, 도서관 이름은 LibraryViewModel을 통해
 * 실제로 저장된 값을 표시한다.
 */
@Composable
fun HomeScreen(
    libraryViewModel: LibraryViewModel,
    onAdminClick: () -> Unit
) {
    val libraryName by libraryViewModel.libraryName.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        TopRightDecoration(modifier = Modifier.align(Alignment.TopEnd))
        BottomLeftDecoration(modifier = Modifier.align(Alignment.BottomStart))

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(0.92f)
                .widthIn(max = 480.dp)
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. 상단: 로고 + 이름 + 인사말
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🌿", style = MaterialTheme.typography.headlineLarge)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = libraryName, style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "안녕하세요", style = MaterialTheme.typography.bodyLarge)
                Text(text = "무엇을 도와드릴까요?", style = MaterialTheme.typography.bodyLarge)
            }

            // 2. 중앙: 큰 메뉴 버튼 3개 (세로 스택)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HomeMenuButton(emoji = "🔍", label = "시설을 찾고 있어요")
                HomeMenuButton(emoji = "📖", label = "이용방법이 궁금해요")
                HomeMenuButton(emoji = "🤖", label = "로봇과 놀아요")
            }

            // 3. 하단: 작은 오늘의 행사 버튼 + 관리자 버튼
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HomeSmallButton(emoji = "📅", label = "오늘의 행사")

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(onClick = onAdminClick) {
                    Text(text = "관리자", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
private fun HomeMenuButton(emoji: String, label: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = emoji, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
private fun HomeSmallButton(emoji: String, label: String) {
    OutlinedButton(onClick = {}) {
        Text(text = "$emoji $label", style = MaterialTheme.typography.labelLarge)
    }
}

/** 자리표시자 배경 장식(점 패턴). 최종 디자인 아님, 본문 버튼을 가리지 않는 모서리에만 배치. */
@Composable
private fun TopRightDecoration(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(24.dp)) {
        repeat(4) {
            Row {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .size(6.dp)
                            .background(Color(0xFFDDE3E1), CircleShape)
                    )
                }
            }
        }
    }
}

/** 자리표시자 배경 장식(원형 블롭). 최종 디자인 아님, 본문 버튼을 가리지 않는 모서리에만 배치. */
@Composable
private fun BottomLeftDecoration(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(start = 0.dp, bottom = 0.dp)
            .size(180.dp)
            .background(Color(0xFFDCF3EC), CircleShape)
    )
}
