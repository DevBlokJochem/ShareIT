/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetnews.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.jetnews.data.account.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UI state for the Interests screen
 */
data class AccountUiState(
    val darkMode: Boolean = false,
    val meldingen: Boolean = false,
    val loading: Boolean = false,
)

class AccountViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(AccountUiState(loading = true))
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    val darkMode =
        accountRepository.observerDarkMode().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    val meldingen =
        accountRepository.observerMeldingen().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            true
        )

    init {
        refreshAll()
    }

    fun toggleDarkMode() {
        viewModelScope.launch {
            accountRepository.toggleDarkMode()
        }
    }

    fun toggleMeldingen() {
        viewModelScope.launch {
            accountRepository.toggleMeldingen()
        }
    }

    /**
     * Refresh topics, people, and publications
     */
    private fun refreshAll() {
        _uiState.update { it.copy(loading = true) }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loading = false,
                    darkMode = false,
                    meldingen = true
                )
            }
        }
    }

    /**
     * Factory for InterestsViewModel that takes PostsRepository as a dependency
     */
    companion object {
        fun provideFactory(
            accountRepository: AccountRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AccountViewModel(accountRepository) as T
            }
        }
    }
}
