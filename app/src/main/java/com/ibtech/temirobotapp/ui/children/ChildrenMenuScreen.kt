package com.ibtech.temirobotapp.ui.children

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import kotlinx.coroutines.launch

private const val NOT_IMPLEMENTED_MESSAGE = "다음 단계에서 구현 예정"

/** UI 테스트용 샘플 메뉴 목록. 퀴즈·추천도서 세부 화면은 이후 단계에서 구현한다. */
private val childrenMenuItems = listOf(
    "오늘의 퀴즈",
    "나에게 맞는 책",
    "도서관 예절",
    "어린이자료실 안내"
)

/**
 * 어린이 콘텐츠 메뉴 화면. 이번 단계에서는 모든 메뉴 버튼이
 * 실제 하위 화면으로 이동하지 않고 스낵바로만 안내한다.
 */
@Composable
fun ChildrenMenuScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    fun notifyNotImplemented() {
        coroutineScope.launch { snackbarHostState.showSnackbar(NOT_IMPLEMENTED_MESSAGE) }
    }

    Scaffold(
        topBar = {
            CommonTopBar(title = "로봇과 놀아요", onBackClick = onBackClick, onHomeClick = onHomeClick)
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(text = "로봇과 놀아요", style = MaterialTheme.typography.headlineLarge)

                    childrenMenuItems.forEach { label ->
                        Button(
                            onClick = { notifyNotImplemented() },
                            modifier = Modifier.fillMaxWidth().height(90.dp)
                        ) {
                            Text(text = label, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }
        }
    }
}
