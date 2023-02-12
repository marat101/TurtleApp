package com.turtleteam.widget.widget.model

import android.content.Context
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.widget.R

data class WidgetSuccessStateData(
    private val days: List<WidgetDay>,
    private val name: String
){
    fun getDayName(day:Int):String = days[day].day
    fun getCountDays() = days.count()
    fun getCountApairs(day: Int):Int = this.days[day].apairs.count()
    fun getNumber(day:Int,position: Int,context: Context):String{
        val apairNumber = this.days[day].apairs[position].number.toString()
        return context.getString(R.string.index_number_apair,apairNumber)
    }
    fun getTime(day: Int, position: Int): String {
        return TIME_ICON + days[day].apairs[position].start + " - " + days[day].apairs[position].end
    }
    fun getDoctrine(day:Int,position: Int):String{
        return DOCTRINE_ICON + this.days[day].apairs[position].doctrine
    }
    fun getTeacher(day:Int,position: Int):String{
        return TEACHER_ICON + this.days[day].apairs[position].teacher
    }
    fun getAuditoria(day:Int,position: Int):String{
        return AUDITORIA_ICON + this.days[day].apairs[position].auditoria
    }
    fun getCorpus(day:Int,position: Int):String{
        return CORPUS_ICON + this.days[day].apairs[position].corpus
    }
    companion object{
        private const val TIME_ICON = "⏳"
        private const val DOCTRINE_ICON = "\uD83D\uDCD6"
        private const val TEACHER_ICON = "\uD83C\uDF93"
        private const val AUDITORIA_ICON = "\uD83D\uDD11"
        private const val CORPUS_ICON = "\uD83C\uDFE2"
        fun fromDaysList(daysList: DaysList): WidgetSuccessStateData {
            daysList.days
            return WidgetSuccessStateData(
                days = daysList.days.map { WidgetDay.fromDays(it) },
                name = daysList.name
            )
        }
    }
}