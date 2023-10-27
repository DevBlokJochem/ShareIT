package com.example.jetnews.data.account.impl

import com.example.jetnews.data.Result
import com.example.jetnews.data.account.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FakeAccountRepository: AccountRepository {

    private val topics by lazy {
        listOf("darkmode", "meldingen")
    }

    val defaultDarkMode = false
    val defaultMeldingen = true

    private val darkMode = MutableStateFlow(defaultDarkMode)
    private val meldingen = MutableStateFlow(defaultMeldingen)
    private val username = MutableStateFlow("gebruiker")

    override suspend fun getTopics(): Result<List<String>> = Result.Success(topics)
    override fun getTopicsFlow(): Flow<Set<String>> = MutableStateFlow(listOf("darkmode" to darkMode, "meldingen" to meldingen).filter { it.second.value }.map { it.first}.toSet() )

    override suspend fun toggleDarkMode() = darkMode.update { !it }
    override suspend fun toggleMeldingen() = meldingen.update { !it }
    override suspend fun setUsername(newName: String) = username.update { newName }

    override fun observerDarkMode(): Flow<Boolean> = darkMode
    override fun observerMeldingen(): Flow<Boolean> = meldingen
    override fun observerUsername(): Flow<String> = username

}