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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import com.ibtech.temirobotapp.data.facility.Facility
import com.ibtech.temirobotapp.data.facility.FakeFacilityRepository
import com.ibtech.temirobotapp.ui.TEMI_PREVIEW_DEVICE
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import com.ibtech.temirobotapp.ui.theme.TemiRobotAppTheme

private const val CATEGORY_ALL = "전체"

/**
 * 전체 장소 목록 화면. 시설 안내 첫 화면의 "다른 장소 찾기"로 진입한다.
 * 주요 장소를 포함한 모든 시설을 카테고리 필터와 함께 보여준다.
 */
@Composable
fun FacilityAllListScreen(
    onFacilityClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val facilities = remember { FakeFacilityRepository.getFacilities() }
    val categories = remember { listOf(CATEGORY_ALL) + FakeFacilityRepository.getCategories() }
    var selectedCategory by remember { mutableStateOf(CATEGORY_ALL) }

    val visibleFacilities = if (selectedCategory == CATEGORY_ALL) {
        facilities
    } else {
        facilities.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            CommonTopBar(title = "전체 장소", onBackClick = onBackClick, onHomeClick = onHomeClick)
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
                    .padding(horizontal = 40.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    categories.forEach { category ->
                        FilterChip(
                            selected = category == selectedCategory,
                            onClick = { selectedCategory = category },
                            label = {
                                Text(category, style = MaterialTheme.typography.titleLarge)
                            },
                            modifier = Modifier.height(64.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(visibleFacilities) { facility ->
                        FacilityRowCard(
                            facility = facility,
                            onClick = { onFacilityClick(facility.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FacilityRowCard(facility: Facility, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = facility.icon, fontSize = 52.sp)

            Spacer(modifier = Modifier.width(20.dp))

            // 이름 아래에 층과 동행 배지를 세로로 둔다. 폰트가 커져도 가로로 붙지 않는다.
            Column(modifier = Modifier.weight(1f)) {
                Text(text = facility.name, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = facility.floor, style = MaterialTheme.typography.bodyLarge)

                    if (facility.escortAvailable) {
                        Spacer(modifier = Modifier.width(12.dp))
                        EscortAvailableBadge()
                    }
                }
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
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true, device = TEMI_PREVIEW_DEVICE)
@Composable
private fun FacilityAllListScreenPreview() {
    TemiRobotAppTheme {
        FacilityAllListScreen(onFacilityClick = {}, onBackClick = {}, onHomeClick = {})
    }
}
