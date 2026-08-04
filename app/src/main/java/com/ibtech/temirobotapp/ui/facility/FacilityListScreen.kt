package com.ibtech.temirobotapp.ui.facility

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibtech.temirobotapp.data.facility.Facility
import com.ibtech.temirobotapp.data.facility.FakeFacilityRepository
import com.ibtech.temirobotapp.ui.components.CommonTopBar

private const val CATEGORY_ALL = "전체"

/**
 * 시설 목록 화면. FakeFacilityRepository가 제공하는 시설 데이터를 표시한다.
 * 검색창은 아직 실제 검색 동작을 연결하지 않은 UI 자리표시자이며,
 * 카테고리 필터는 로컬 데이터 기준으로 실제 동작한다.
 */
@Composable
fun FacilityListScreen(
    onFacilityClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val facilities = remember { FakeFacilityRepository.getFacilities() }
    val categories = remember { listOf(CATEGORY_ALL) + FakeFacilityRepository.getCategories() }
    var selectedCategory by remember { mutableStateOf(CATEGORY_ALL) }
    var searchText by remember { mutableStateOf("") }

    val visibleFacilities = if (selectedCategory == CATEGORY_ALL) {
        facilities
    } else {
        facilities.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            CommonTopBar(title = "시설 안내", onBackClick = onBackClick, onHomeClick = onHomeClick)
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
                    modifier = Modifier.widthIn(max = 960.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "어디를 안내해 드릴까요?",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
                    )

                    // 검색창: 아직 실제 검색 로직은 연결하지 않은 자리표시자다.
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("시설 이름으로 검색") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        categories.forEach { category ->
                            FilterChip(
                                selected = category == selectedCategory,
                                onClick = { selectedCategory = category },
                                label = { Text(category) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(visibleFacilities) { facility ->
                            FacilityCard(facility = facility, onClick = { onFacilityClick(facility.id) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FacilityCard(facility: Facility, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = facility.icon, style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = facility.name, style = MaterialTheme.typography.titleLarge)
                Text(text = facility.floor, style = MaterialTheme.typography.bodyLarge)
            }

            if (facility.escortAvailable) {
                EscortAvailableBadge()
            }
        }
    }
}

/** 동행 가능 시설임을 나타내는 작은 배지. */
@Composable
private fun EscortAvailableBadge() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Text(
            text = "🤖 동행 가능",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
private fun FacilityListScreenPreview() {
    FacilityListScreen(onFacilityClick = {}, onBackClick = {}, onHomeClick = {})
}
