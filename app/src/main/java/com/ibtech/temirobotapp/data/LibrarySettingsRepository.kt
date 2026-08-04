package com.ibtech.temirobotapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.librarySettingsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "library_settings"
)

/**
 * 도서관 기본정보(현재는 이름만) 설정 저장소.
 * REQUIREMENTS.md 18장 "설정 데이터는 앱 재시작 후에도 유지되어야 한다"를 충족하기 위해
 * DataStore Preferences로 영구 저장한다.
 */
class LibrarySettingsRepository(private val context: Context) {

    val libraryName: Flow<String> = context.librarySettingsDataStore.data.map { prefs ->
        prefs[LIBRARY_NAME_KEY] ?: DEFAULT_LIBRARY_NAME
    }

    suspend fun updateLibraryName(name: String) {
        context.librarySettingsDataStore.edit { prefs ->
            prefs[LIBRARY_NAME_KEY] = name
        }
    }

    suspend fun resetLibraryName() {
        context.librarySettingsDataStore.edit { prefs ->
            prefs[LIBRARY_NAME_KEY] = DEFAULT_LIBRARY_NAME
        }
    }

    companion object {
        private val LIBRARY_NAME_KEY = stringPreferencesKey("library_name")
        const val DEFAULT_LIBRARY_NAME = "테미 도서관"
    }
}
