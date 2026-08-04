package com.ibtech.temirobotapp.ui.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import com.ibtech.temirobotapp.ui.components.CommonTopBar

/**
 * 행사 목록 화면. 관리자가 등록한 행사 및 프로그램을 표시한다.
 * 현재는 FakeEvent 샘플 데이터를 사용한다.
 */
@Composable
fun EventListScreen(
    onEventClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopBar(title = "오늘의 행사", onBackClick = onBackClick, onHomeClick = onHomeClick)
        }
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
                        .widthIn(max = 640.dp)
                        .padding(top = 32.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(text = "오늘의 행사", style = MaterialTheme.typography.headlineLarge)

                    fakeEvents.forEach { event ->
                        EventCard(event = event, onClick = { onEventClick(event.id) })
                    }
                }
            }
        }
    }
}

@Composable
private fun EventCard(event: FakeEvent, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = event.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "${event.date} · ${event.place}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
private fun EventListScreenPreview() {
    EventListScreen(onEventClick = {}, onBackClick = {}, onHomeClick = {})
}
