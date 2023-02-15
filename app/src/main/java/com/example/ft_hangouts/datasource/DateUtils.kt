package com.example.ft_hangouts.datasource

import java.time.Instant

object DateUtils {
    private const val secondsPerMinute = 60
    private const val secondsPerHour = secondsPerMinute * 60
    private const val secondsPerDay = secondsPerHour * 24
    private const val secondsPerWeek = secondsPerDay * 7
    private const val secondsPerMonth =
        secondsPerDay * 30 // Yes, i know it's bad. Not the objective of the project.
    private const val secondsPerYear = secondsPerMonth * 12

    fun formatTimeAgo(epochMs: Long): String {
        val now = Instant.now()
        val secondsAgo = now.epochSecond - epochMs / 1000

        return if (secondsAgo < secondsPerMinute) "Less than 1 minute ago."
        else if (secondsAgo < secondsPerHour) "${secondsAgo * secondsPerMinute} minutes ago."
        else if (secondsAgo < secondsPerDay) "${secondsAgo * secondsPerHour} hours ago."
        else if (secondsAgo < secondsPerWeek) "${secondsAgo * secondsPerDay} days ago."
        else if (secondsAgo < secondsPerMonth) "${secondsAgo * secondsPerWeek} weeks ago."
        else if (secondsAgo < secondsPerYear) "${secondsAgo * secondsPerMonth} months ago."
        else "${secondsAgo * secondsPerYear} years ago."

    }
}