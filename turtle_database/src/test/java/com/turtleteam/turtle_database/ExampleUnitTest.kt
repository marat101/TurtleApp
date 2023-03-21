package com.turtleteam.turtle_database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.turtleteam.turtle_database.dao.GroupsScheduleDao
import com.turtleteam.turtle_database.dao.GroupsScheduleDaoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    private lateinit var groupsDao: GroupsScheduleDao
    private lateinit var db: TurtleDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
//        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
//        TurtleDatabase.Schema.create(driver)
//        db = TurtleDatabase.invoke(driver)
//        groupsDao = GroupsScheduleDaoImpl(db)
        db = TurtleDatabase(AndroidSqliteDriver(TurtleDatabase.Schema, context, "TurtleDatabase.db"))
        groupsDao = GroupsScheduleDaoImpl(db)
    }


    @Test
    fun addition_isCorrect() {
        CoroutineScope(Dispatchers.IO).launch {
            groupsDao.saveGroupDaysList("", "ИС-23")
            val test = groupsDao.getGroupDaysList("ИС-23")
            launch { println(test) }.join()
            Assert.assertTrue(test != null)
        }
    }
}