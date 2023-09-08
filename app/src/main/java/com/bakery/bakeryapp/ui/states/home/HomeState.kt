package com.bakery.bakeryapp.ui.states.home

import com.bakery.bakeryapp.domain.model.user.User

data class HomeState(
    val user: User? = User("", "", "", "", "", "", "", "", "")
)
