package com.ibtech.temirobotapp.ui.facility

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibtech.temirobotapp.data.facility.Facility
import com.ibtech.temirobotapp.data.facility.FakeFacilityRepository
import com.ibtech.temirobotapp.ui.TEMI_PREVIEW_DEVICE
import com.ibtech.temirobotapp.ui.components.CommonTopBar
import com.ibtech.temirobotapp.ui.theme.TemiRobotAppTheme

/**
 * 시설 안내 첫 화면. 주요 장소만 2x2 큰 버튼으로 보여주고,
 * 나머지 장소는 "다른 장소 찾기"에서 전체 목록으로 확인한다.
 *
 * 방문 목적이 뚜렷한 이용자가 한 번에 목적지를 고를 수 있게 첫 화면은 4개로 제한한다.
 * (검색은 제공하지 않는다. 장소 수가 많지 않고, 키보드 입력이 어르신에게 부담이 된다.)
 */
@Composable
fun FacilityListScreen(
    onFacilityClick: (String) -> Unit,
    onFindOtherClick: () -> Unit,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val primaryFacilities = remember { FakeFacilityRepository.getPrimaryFacilities() }

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 48.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "어디를 안내해 드릴까요?",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                primaryFacilities.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(28.dp)
                    ) {
                        rowItems.forEach { facility ->
                            PrimaryFacilityCard(
                                facility = facility,
                                onClick = { onFacilityClick(facility.id) },
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedButton(
                    onClick = onFindOtherClick,
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                ) {
                    Text(text = "다른 장소 찾기", style = MaterialTheme.typography.headlineLarge)
                }
            }
        }
    }
}

/** 주요 장소 카드. 아이콘을 크게 위에 두고 이름을 아래에 둔다. */
@Composable
private fun PrimaryFacilityCard(
    facility: Facility,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 홈·이용방법 화면의 메뉴 카드와 같은 민트 컨테이너 색을 쓴다.
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
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
            Text(text = facility.icon, fontSize = 76.sp)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = facility.name,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, device = TEMI_PREVIEW_DEVICE)
@Composable
private fun FacilityListScreenPreview() {
    TemiRobotAppTheme {
        FacilityListScreen(
            onFacilityClick = {},
            onFindOtherClick = {},
            onBackClick = {},
            onHomeClick = {}
        )
    }
}
