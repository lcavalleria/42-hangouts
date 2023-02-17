package com.lcavalle.ft_hangouts.datasource

import android.content.res.Configuration
import androidx.core.os.ConfigurationCompat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {
    private const val secondsPerMinute = 60
    private const val secondsPerHour = secondsPerMinute * 60
    private const val secondsPerDay = secondsPerHour * 24

    /**
     * [currentConfig] get in Composable by doing LocalConfiguration.current
     */
    private fun formatterForLocale(currentConfig: Configuration) = DateTimeFormatter
        .ofPattern("HH:mm, dd LLL, yyyy")
        .withLocale(ConfigurationCompat.getLocales(currentConfig).get(0))
        .withZone(TimeZone.getDefault().toZoneId())


    fun formatRelativeDateTime(epochMs: Long, currentConfig: Configuration): String {
        val now = Instant.now()
        val secondsAgo = now.epochSecond - epochMs / 1000

        return if (secondsAgo < secondsPerMinute) "Less than 1 minute ago."
        else if (secondsAgo < secondsPerHour) "${secondsAgo / secondsPerMinute} minutes ago."
        else if (secondsAgo < secondsPerDay) "${secondsAgo / secondsPerHour} hours ago."
        else formatterForLocale(currentConfig).format(Instant.ofEpochMilli(epochMs))

    }
}