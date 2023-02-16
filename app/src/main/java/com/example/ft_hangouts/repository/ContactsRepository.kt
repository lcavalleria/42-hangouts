package com.example.ft_hangouts.repository

import androidx.compose.ui.graphics.ImageBitmap
import com.example.ft_hangouts.datasource.ContactDao
import com.example.ft_hangouts.viewModel.Contact
import java.time.Instant
import java.util.UUID

object ContactsRepository {


    // todo
    private val contactDaoPlaceholder = arrayOf(
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Lluis Cavalleria",
            "639516183",
            "jaja@jaja.ja",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
        ContactDao(
            "Ton Pare",
            "599999999",
            "tonpare@ton.pa",
            ImageBitmap(19, 30),
            false,
            Instant.now()
        ),
        ContactDao(
            "Soviet Elmo",
            "677777776",
            "tripaloski@sov.ru",
            ImageBitmap(19, 30),
            true,
            Instant.now()
        ),
    )

    private fun fetchContacts(): List<Contact> {
        return contactDaoPlaceholder.map { cdo ->
            return@map Contact(
                id = UUID.randomUUID().toString(),
                name = cdo.name,
                number = cdo.number,
                mail = cdo.mail,
                picture = cdo.picture,
                isFavorite = cdo.isFavorite,
                lastOnline = cdo.lastOnline.toEpochMilli()
            )
        }
    }
    val placeholder = fetchContacts()
}