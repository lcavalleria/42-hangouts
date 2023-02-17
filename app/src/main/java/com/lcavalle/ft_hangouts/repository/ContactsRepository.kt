package com.lcavalle.ft_hangouts.repository

import androidx.compose.ui.graphics.ImageBitmap
import com.lcavalle.ft_hangouts.datasource.ContactDao
import com.lcavalle.ft_hangouts.viewModel.Contact
import java.time.Instant
import java.util.*

object ContactsRepository {
    // todo
    private val contactsDaoPlaceholder = arrayOf(
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
        return contactsDaoPlaceholder.map { cdo ->
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

    val contactsPlaceholder = fetchContacts()

    /**
     * @return <TimestampMs, Pair<isMine, Message>>
     */
    fun messagesPlaceholderById(id: String): Map<Long, Pair<Boolean, String>> {
        val now = System.currentTimeMillis()
        return mapOf<Long, Pair<Boolean, String>>(
            Pair(
                now - 7000000000,
                Pair(true, "try to send some \n multiline text to see \n how it works")
            ),
            Pair(
                now - 5200000000,
                Pair(
                    false,
                    "also try with a very long text in one line to see how it will truncate it or if it will let me scroll to see it or it will cut in some random place and not let me read everything in a nice way that is intuitive and comfortable like the sofa we got in the apartment, which was in discount from 900 euro to 300 and its super good and comfortable but its like a plastic thing so in summer its hot and sticky."
                )
            ),
            Pair(now - 500000000, Pair(true, "interesting")),
            Pair(now - 30000000, Pair(true, "yes or no")),
            Pair(now - 10000000, Pair(false, "hahahahahha")),
            Pair(now - 5000000, Pair(false, "u stupid or w0t m8")),
            Pair(now - 20000, Pair(true, "kill me please")),
            Pair(now - 5000, Pair(false, "im tired and want to sleep")),
        )
    }
}