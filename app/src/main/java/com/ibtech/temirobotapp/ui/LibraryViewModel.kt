package com.ibtech.temirobotapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ibtech.temirobotapp.data.LibrarySettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * 도서관 이름 상태를 HomeScreen과 AdminScreen이 함께 사용할 수 있도록 공유하는 ViewModel.
 * Activity 범위로 하나만 생성되어 두 화면에 동일한 인스턴스가 전달된다.
 */
class LibraryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LibrarySettingsRepository(application)

    val libraryName: StateFlow<String> = repository.libraryName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LibrarySettingsRepository.DEFAULT_LIBRARY_NAME
    )

    fun updateLibraryName(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch { repository.updateLibraryName(name) }
    }

    fun resetLibraryName() {
        viewModelScope.launch { repository.resetLibraryName() }
    }
}
