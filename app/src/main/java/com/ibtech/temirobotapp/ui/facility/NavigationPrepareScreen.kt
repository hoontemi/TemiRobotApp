package com.ibtech.temirobotapp.ui.facility

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ibtech.temirobotapp.data.facility.FakeFacilityRepository
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import kotlinx.coroutines.launch

private const val NOT_IMPLEMENTED_MESSAGE = "다음 단계에서 구현 예정"

/**
 * 이동 준비 화면. 시설 상세에서 "동행 안내"를 선택하면 바로 이동하지 않고
 * 이 화면을 먼저 표시한다. "안내 시작"을 눌러도 이번 단계에서는 실제 이동을
 * 시작하지 않고 스낵바로만 안내한다. "취소"는 시설 상세 화면으로 돌아간다.
 */
@Composable
fun NavigationPrepareScreen(
    facilityId: String,
    onCancelClick: () -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val facility = FakeFacilityRepository.getFacilityById(facilityId)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CommonTopBar(title = "동행 안내 준비", onBackClick = onBackClick, onHomeClick = onHomeClick)
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
                    Text(
                        text = "${facility?.name ?: "목적지"}로\n안내를 시작합니다.",
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "안전하게 따라와 주세요.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(NOT_IMPLEMENTED_MESSAGE)
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(64.dp)
                    ) {
                        Text(text = "안내 시작", style = MaterialTheme.typography.titleLarge)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = onCancelClick) {
                        Text(text = "취소", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }
}
