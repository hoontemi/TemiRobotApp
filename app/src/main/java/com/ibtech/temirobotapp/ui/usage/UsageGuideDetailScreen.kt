package com.ibtech.temirobotapp.ui.usage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibtech.temirobotapp.ui.TEMI_PREVIEW_DEVICE
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import com.ibtech.temirobotapp.ui.theme.TemiRobotAppTheme
import kotlinx.coroutines.launch

private const val TTS_NOT_IMPLEMENTED_MESSAGE = "음성 안내는 다음 단계에서 구현 예정입니다."

/**
 * 이용방법 세부 안내 화면. 제목·요약·상세 내용을 표시하고
 * "안내 듣기"와 "관련 시설 안내" 버튼을 제공한다.
 *
 * - 안내 듣기: 실제 TTS는 아직 붙이지 않고 스낵바로만 안내한다.
 * - 관련 시설 안내: relatedFacilityId가 있는 항목에서만 노출한다.
 *   (relatedPoiName만 있는 항목은 시설로 등록되어 있지 않아 이동할 대상이 없다.
 *   이후 실제 POI 조회가 붙으면 그때 POI 기준으로 연결한다.)
 */
@Composable
fun UsageGuideDetailScreen(
    guideId: String,
    onRelatedFacilityClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val guide = getUsageGuide(guideId)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CommonTopBar(
                title = guide?.title ?: "이용방법 안내",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 56.dp, vertical = 32.dp)
                ) {
                    // 요약: 3줄 이내 핵심 답변을 강조해서 먼저 보여준다.
                    Surface(
                        shape = RoundedCornerShape(28.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = guide?.summary ?: "안내 내용을 찾을 수 없습니다.",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(horizontal = 36.dp, vertical = 32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // 상세 내용: 문구가 길어질 수 있어 이 영역만 스크롤한다.
                    // 어르신도 멀리서 읽을 수 있도록 본문 기본값보다 크게, 줄간격도 넓게 잡는다.
                    Text(
                        text = guide?.content.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 32.sp,
                        lineHeight = 50.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(28.dp)
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(TTS_NOT_IMPLEMENTED_MESSAGE)
                                }
                            },
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(104.dp)
                        ) {
                            Text(text = "🔊 안내 듣기", style = MaterialTheme.typography.headlineLarge)
                        }

                        // 연결할 시설이 있는 항목에서만 노출한다.
                        guide?.relatedFacilityId?.let { facilityId ->
                            OutlinedButton(
                                onClick = { onRelatedFacilityClick(facilityId) },
                                shape = RoundedCornerShape(24.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(104.dp)
                            ) {
                                Text(
                                    text = "📍 관련 시설 안내",
                                    style = MaterialTheme.typography.headlineLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/** 관련 시설이 연결된 항목: "안내 듣기" + "관련 시설 안내" 두 버튼이 모두 보인다. */
@Preview(name = "관련 시설 있음", showBackground = true, device = TEMI_PREVIEW_DEVICE)
@Composable
private fun UsageGuideDetailWithFacilityPreview() {
    TemiRobotAppTheme {
        UsageGuideDetailScreen(
            guideId = "borrow-self-return",
            onRelatedFacilityClick = {},
            onBackClick = {},
            onHomeClick = {}
        )
    }
}

/** 관련 시설이 없는 항목: "안내 듣기" 하나만 보인다. */
@Preview(name = "관련 시설 없음", showBackground = true, device = TEMI_PREVIEW_DEVICE)
@Composable
private fun UsageGuideDetailWithoutFacilityPreview() {
    TemiRobotAppTheme {
        UsageGuideDetailScreen(
            guideId = "reading-notice",
            onRelatedFacilityClick = {},
            onBackClick = {},
            onHomeClick = {}
        )
    }
}
