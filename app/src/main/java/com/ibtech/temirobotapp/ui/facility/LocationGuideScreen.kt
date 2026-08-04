package com.ibtech.temirobotapp.ui.facility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.ibtech.temirobotapp.data.facility.FakeFacilityRepository
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import kotlinx.coroutines.launch

private const val NOT_IMPLEMENTED_MESSAGE = "다음 단계에서 구현 예정"

/**
 * 위치만 보기 화면. 시설 상세에서 "위치만 보기"를 선택하면 표시된다.
 * 뒤로가기·홈 이동은 다른 하위 화면과 동일하게 공통 상단바(CommonTopBar)로 처리하며,
 * 본문에는 시설 고유 동작인 "안내 듣기"만 버튼으로 둔다.
 */
@Composable
fun LocationGuideScreen(
    facilityId: String,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val facility = FakeFacilityRepository.getFacilityById(facilityId)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CommonTopBar(
                title = facility?.name ?: "위치 안내",
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.widthIn(max = 480.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                RoundedCornerShape(24.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = facility?.icon ?: "🏢",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = facility?.name ?: "알 수 없는 시설",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = facility?.floor ?: "-", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = facility?.locationGuideText ?: "위치 안내 문구가 없습니다.",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(NOT_IMPLEMENTED_MESSAGE)
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(64.dp)
                    ) {
                        Text(text = "안내 듣기", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }
}
