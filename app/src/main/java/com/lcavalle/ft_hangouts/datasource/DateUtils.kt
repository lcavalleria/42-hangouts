package com.lcavalle.ft_hangouts.datasource

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.os.ConfigurationCompat
import com.example.ft_hangouts.R
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
        .ofPattern("HH:mm, dd/LL/yyyy")
        .withLocale(ConfigurationCompat.getLocales(currentConfig).get(0))
        .withZone(TimeZone.getDefault().toZoneId())

    fun formatDate(epochMs: Long, currentConfig: Configuration): String {
        return formatterForLocale(currentConfig).format(Instant.ofEpochMilli(epochMs))
    }

    @Composable
    fun formatRelativeDateTime(epochMs: Long, currentConfig: Configuration): String {
        val now = Instant.now()
        val secondsAgo = now.epochSecond - epochMs / 1000

        return if (secondsAgo < secondsPerMinute)
            stringResource(id = R.string.less_1_min)
        else if (secondsAgo < secondsPerHour)
            stringResource(id = R.string.x_minutes_ago, secondsAgo / secondsPerMinute)
        else if (secondsAgo < secondsPerDay)
            stringResource(id = R.string.x_hours_ago, secondsAgo / secondsPerHour)
        else formatDate(epochMs, currentConfig)

    }
}