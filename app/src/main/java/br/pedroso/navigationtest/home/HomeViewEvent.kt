package br.pedroso.navigationtest.home

sealed class HomeViewEvent {
    data class BottomNavigationItemClicked(
        val clickedItem: String,
        val navigationAction: () -> Unit
    ) : HomeViewEvent()

    data class EditorButtonClicked(val navigationAction: () -> Unit) : HomeViewEvent()
}
