package com.ibtech.temirobotapp.ui.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibtech.temirobotapp.ui.LibraryViewModel

/**
 * 관리자 화면. 이번 단계에서는 도서관 이름 편집·초기화만 제공한다.
 * (나머지 관리 기능은 이후 단계에서 추가)
 */
@Composable
fun AdminScreen(
    libraryViewModel: LibraryViewModel,
    onBack: () -> Unit
) {
    val libraryName by libraryViewModel.libraryName.collectAsState()
    var nameInput by remember(libraryName) { mutableStateOf(libraryName) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .widthIn(max = 480.dp)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "도서관 이름 설정", style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("도서관 이름") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { libraryViewModel.updateLibraryName(nameInput) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("저장")
                    }
                    OutlinedButton(
                        onClick = { libraryViewModel.resetLibraryName() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("초기화")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                TextButton(onClick = onBack) {
                    Text("뒤로가기")
                }
            }
        }
    }
}
