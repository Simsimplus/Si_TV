package io.simsim.iptv.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.simsim.iptv.utils.M3u8Item
import io.simsim.iptv.utils.M3u8Parser
import io.simsim.iptv.utils.isServerReachable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import splitties.init.appCtx

class MainViewModel : ViewModel() {
    private val _mainState = MutableStateFlow<MainState>(MainState.Loading)
    val mainState = _mainState.asStateFlow()

    init {
        viewModelScope.launch {
            val tmpList = mutableListOf<M3u8Item>()
            M3u8Parser.parse(appCtx.resources.assets.open("ggtv.m3u")).forEach {
                viewModelScope.launch {
                    if (it.url.isServerReachable()) {
                        tmpList.add(it)
                    }
                }
            }
            delay(3500)
            _mainState.value = MainState.Success(tmpList)
        }

    }
}

sealed class MainState {
    object Loading : MainState()
    class Failure(throwable: Throwable) : MainState()
    class Success(val list: List<M3u8Item>) : MainState()
}