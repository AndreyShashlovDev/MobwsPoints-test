package com.sprinter.mobws.lists

sealed class PageableLoadedState {
    object Loaded : PageableLoadedState()
    object Loading : PageableLoadedState()
    data class Error(val e: Throwable) : PageableLoadedState()
}
