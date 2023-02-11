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
        return days[day].apairs[position].start + " - " + days[day].apairs[position].end
    }
    fun getDoctrine(day:Int,position: Int):String{
        return this.days[day].apairs[position].doctrine
    }
    fun getTeacher(day:Int,position: Int):String{
        return this.days[day].apairs[position].teacher
    }
    fun getAuditoria(day:Int,position: Int):String{
        return this.days[day].apairs[position].auditoria
    }
    fun getCorpus(day:Int,position: Int):String{
        return this.days[day].apairs[position].corpus
    }
    companion object{
        fun fromDaysList(daysList: DaysList): WidgetSuccessStateData {
            daysList.days
            return WidgetSuccessStateData(
                days = daysList.days.map { WidgetDay.fromDays(it) },
                name = daysList.name
            )
        }
    }
}