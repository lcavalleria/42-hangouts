package com.example.ft_hangouts.datasource

import java.time.Instant

object DateUtils {
    const val secondsPerMinute = 60
    const val secondsPerHour = secondsPerMinute * 60
    const val secondsPerDay = secondsPerHour * 24
    const val secondsPerWeek = secondsPerDay * 7
    const val secondsPerMonth =
        secondsPerDay * 30 // Yes, i know it's bad. Not the objective of the project.
    const val secondsPerYear = secondsPerMonth * 12

    fun formatTimeAgo(instant: Instant): String {
        val now = Instant.now()
        val secondsAgo = now.epochSecond - instant.epochSecond


        return if (secondsAgo < secondsPerMinute) "Less than 1 minute ago."
        else if (secondsAgo < secondsPerHour) "${secondsAgo * secondsPerMinute} minutes ago."
        else if (secondsAgo < secondsPerDay) "${secondsAgo * secondsPerHour} hours ago."
        else if (secondsAgo < secondsPerWeek) "${secondsAgo * secondsPerDay} days ago."
        else if (secondsAgo < secondsPerMonth) "${secondsAgo * secondsPerWeek} weeks ago."
        else if (secondsAgo < secondsPerYear) "${secondsAgo * secondsPerMonth} months ago."
        else "${secondsAgo * secondsPerYear} years ago."

    }
}