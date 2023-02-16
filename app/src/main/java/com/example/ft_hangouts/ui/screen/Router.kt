package com.example.ft_hangouts.ui.screen

sealed interface Router {

    // const val PictureSelector = "pictureSelector"

    object Home : Router {
        const val route = "home"
    }

    object Details : Router {
        const val route = "details"
        const val argId = "id"

        fun withId(): String {
            return "${route}/{${argId}}"
        }

        fun withId(id: String): String {
            return "${route}/${id}"
        }
    }

    object Edit : Router {
        const val route = "edit"
        const val argId = "id"

        fun withId(): String {
            return "${route}/{${argId}}"
        }

        fun withId(id: String): String {
            return "${route}/${id}"
        }
    }
}
