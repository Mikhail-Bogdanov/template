package com.evo.domain

import kotlinx.datetime.*
import kotlin.time.*
import kotlin.time.Clock
import kotlin.time.Instant

/**
 * [LocalTime] is day time (09:00, 11:27, etc)
 * [LocalDate] is date from epoch (01.01.1970) without a reference to any timezone
 * [LocalDateTime] is date with time from epoch (01.01.1970).
 * Also it can represent date and time at a specific timezone
 * [LocalDateRange] is range from one date to another date
 *
 * [Instant] is a moment in time (date + time) without a reference to ant timezone.
 * Can be converted to milliseconds/nanoseconds/seconds of epoch
 * and to [LocalDateTime]
 * Can be created from milliseconds/nanoseconds/seconds of epoch
 * [TimeZone] is any timezone e.g. [TimeZone.currentSystemDefault]
 *
 * [DayOfWeek] is day of week, lol. Supports day numbers, where 1 - MONDAY, 7 - SUNDAY
 * [DateTimeUnit] is time unit to plus/minus with [LocalDate]
 *
 * [Clock.System] is system clock which represents system time from epoch
 * [Clock.System.now] is system current [Instant]
 */

@OptIn(ExperimentalTime::class)
object EvoDateTime {

    val now get() = Clock.System.now()
    val defaultZone get() = TimeZone.currentSystemDefault()

    val dateTime get() = now.toLocalDateTime(defaultZone)
    val time get() = dateTime.time
    val date get() = dateTime.date

}
