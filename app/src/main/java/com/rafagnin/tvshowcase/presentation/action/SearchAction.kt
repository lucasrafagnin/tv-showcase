package com.rafagnin.tvshowcase.presentation.action

sealed class SearchAction {
    object Retry: SearchAction()
    data class Query(
        val text: String
    ) : SearchAction()
}
