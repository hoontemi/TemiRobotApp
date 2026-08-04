package com.ibtech.temirobotapp.ui.event

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
import androidx.compose.ui.unit.dp
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import kotlinx.coroutines.launch

private const val NOT_IMPLEMENTED_MESSAGE = "다음 단계에서 구현 예정"

/**
 * 행사 상세 화면. 행사 장소 안내 / 신청 안내 버튼은
 * 이번 단계에서 실제 QR·POI·네트워크 기능을 연결하지 않고 스낵바로만 안내한다.
 */
@Composable
fun EventDetailScreen(
    eventId: String,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val event = fakeEvents.find { it.id == eventId }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    fun notifyNotImplemented() {
        coroutineScope.launch { snackbarHostState.showSnackbar(NOT_IMPLEMENTED_MESSAGE) }
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = event?.name ?: "행사 상세",
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
                    Text(
                        text = event?.name ?: "알 수 없는 행사",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "${event?.date ?: "-"}  ${event?.time ?: ""}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = event?.place ?: "-", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "대상: ${event?.target ?: "-"}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = event?.description ?: "행사 설명이 없습니다.",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { notifyNotImplemented() },
                        modifier = Modifier.fillMaxWidth().height(64.dp)
                    ) {
                        Text(text = "행사 장소 안내", style = MaterialTheme.typography.titleLarge)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = { notifyNotImplemented() },
                        modifier = Modifier.fillMaxWidth().height(64.dp)
                    ) {
                        Text(text = "신청 안내", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }
}
