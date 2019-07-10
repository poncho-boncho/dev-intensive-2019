package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format (pattern:String="HH:mm:ss dd.MM.yy"): String? {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.add (value:Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}
fun Date.humanizeDiff(date: Date =Date()): String {
    var razDateSecond: Int = ((date.time - this.time)/ 1000).toInt()
    var razDateMinute: Int = ((date.time - this.time)/(1000* 60)).toInt()
    var razDateHour:   Int = ((date.time - this.time)/(1000*60*60)).toInt()
    var razDateDay: Int = ((date.time - this.time)/(1000*60*60*24)).toInt()

    var longnumberM:String = razDateMinute.toString()
    var lastnumberM:Int =longnumberM.length-1
    var goodWordMinute =

    when (longnumberM[lastnumberM].toInt()) {
        49 ->"минуту" //1
        in 50..52 ->"минуты" //2..4
        in 53..57 -> "минут" //5..10
        48 -> "минут" //0

        else -> "Опять забыл что из массива один символ переводится в toInt как значение в таблице ASII"
    }
    var longnumberH:String = razDateHour.toString()
    var lastnumberH:Int =longnumberH.length-1
    var goodWordHour =

        when (longnumberH[lastnumberH].toInt()) {
            49 ->"час" //1
            in 50..52 ->"часа" //2..4
            in 53..57 -> "часов" //5..10
            48 -> "часов" //0

            else -> "Опять забыл что из массива один символ переводится в toInt как значение в таблице ASII"
        }

    var longnumberD:String = razDateDay.toString()
    var lastnumberD:Int =longnumberD.length-1
    var goodWordDay =

        when (longnumberD[lastnumberD].toInt()) {
            49 ->"день" //1
            in 50..52 ->"дня" //2..4
            in 53..57 -> "дней" //5..10
            48 -> "дней" //0

            else -> "Опять забыл что из массива один символ переводится в toInt как значение в таблице ASII"
        }

    when(razDateSecond){

        in 0..1 -> return "только что"
        in 1..45 -> return "несколько секунд назад"
        in 45..75 -> return "минуту назад"
    }

    when (razDateMinute) {


        in 1..10 -> return "$razDateMinute $goodWordMinute назад"
        in 11..20 -> return "$razDateMinute минут назад"
        in 21..45 -> return "$razDateMinute $goodWordMinute назад"
        in 45..75 -> return "час назад"


    }
    when (razDateHour) {


        in 1..10  -> return "$razDateHour $goodWordHour назад"
        in 11..20 -> return "$razDateHour часов назад"
        in 21..22 -> return  "$razDateHour $goodWordHour назад"
        in 22..26 -> return  "День назад"


    }
    when (razDateDay) {


        in 1..10 -> return "$razDateDay $goodWordDay назад"
        in 11..20 -> return "$razDateDay дней назад"
        in 20..360 -> return "$razDateDay $goodWordDay назад"

        else ->return "более года назад"
    }


}

enum class TimeUnits{
        SECOND,
        MINUTE,
        HOUR,
        DAY
    }



