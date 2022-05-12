package com.example.whacamole.screens.menu

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.plays.GetRecordUseCase
import com.example.whacamole.navigation.NavigationDestinations
import com.example.whacamole.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getRecordUseCase: GetRecordUseCase
): ViewModel() {

    private val _menuScreenState = mutableStateOf<MenuScreenState>(MenuScreenState.NoRecord)
    val menuScreenState: State<MenuScreenState> = _menuScreenState

    init {
        getRecord()
    }

    private fun getRecord() {
        viewModelScope.launch {
            val record = getRecordUseCase.execute()
            if (record != null) {
                _menuScreenState.value =
                    MenuScreenState.HasRecord(record)
            } else {
                _menuScreenState.value =
                    MenuScreenState.NoRecord
            }
        }
    }

    fun goToPlayScreen() {
        navigator.navigate(NavigationDestinations.GameScreen)
    }
}