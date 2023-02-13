package com.example.ft_hangouts.datasource

import androidx.compose.ui.graphics.ImageBitmap
import java.time.Instant

data class Contact(
    val name: String,
    val number: String,
    val mail: String,
    val picture: ImageBitmap,
    val isFavorite: Boolean,
    val lastReceivedTime: Instant
) {
    companion object Contact {
        val placeholder = arrayOf(
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Lluis Cavalleria",
                "639516183",
                "jaja@jaja.ja",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
            Contact(
                "Ton Pare",
                "599999999",
                "tonpare@ton.pa",
                ImageBitmap(19, 30),
                false,
                Instant.now()
            ),
            Contact(
                "Soviet Elmo",
                "677777776",
                "tripaloski@sov.ru",
                ImageBitmap(19, 30),
                true,
                Instant.now()
            ),
        )
    }
}
