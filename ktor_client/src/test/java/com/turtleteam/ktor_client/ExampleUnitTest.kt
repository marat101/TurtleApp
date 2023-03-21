package com.turtleteam.ktor_client

import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ktor_client.api.ApiService
import com.turtleteam.ktor_client.api.Ktor
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class KtorClientTest {

    private lateinit var api: ApiService
    private var sc = DaysList(emptyList(), "AAAAAA")

    @Test
    fun getSchedule() {
        CoroutineScope(Dispatchers.IO).launch {
            api = Ktor(HttpClient(OkHttp))
            val schedule = api.getSchedule("ИС-23")
            sc = schedule
            println(schedule)
            assertEquals(schedule, null)
        }
    }

    @After
    fun test(){
        println(sc)
    }
}