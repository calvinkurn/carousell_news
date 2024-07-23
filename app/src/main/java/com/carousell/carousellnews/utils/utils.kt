package com.carousell.carousellnews.utils

import java.time.Duration
import java.time.Instant

fun Long.toDateCreationFormat(): String {
    val pastInstant = Instant.ofEpochSecond(this)
    val currentInstant = Instant.now()
    val diff = Duration.between(pastInstant, currentInstant)

    val days = diff.toDays()
    val week = days / 7
    val month = week / 4
    val year = month / 12

    val hour = diff.toHours()
    val minute = diff.toMinutes()

    return if (year > 0) {
        "$year years ago"
    } else if (month > 0) {
        "$month months ago"
    } else if (week > 0) {
        "$week weeks ago"
    } else if (days > 0) {
        "$days days ago"
    } else if (hour > 0) {
        "$hour hours ago"
    } else if (minute > 0) {
        "$minute minutes ago"
    } else "Few seconds ago"
}