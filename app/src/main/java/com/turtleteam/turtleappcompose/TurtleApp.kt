package com.turtleteam.turtleappcompose

import android.app.Application
import com.turtleteam.data.di.dataModule
import com.turtleteam.ktor_client.di.networkModule
import com.turtleteam.notification_service.workers.SaveNotificationWorker
import com.turtleteam.remote_database.UpdateService
import com.turtleteam.remote_database.di.firestoreModule
import com.turtleteam.turtle_database.di.databaseModule
import com.turtleteam.turtleappcompose.di.domainModule
import com.turtleteam.turtleappcompose.di.repositoryModule
import com.turtleteam.ui.di.uiModule
import com.turtleteam.widget_schedule.di.widgetModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class TurtleApp : Application() {

    private val applicationScope = MainScope()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.BUILD_TYPE != "release") Level.ERROR else Level.DEBUG)

            androidContext(this@TurtleApp)
            modules(
                listOf(
                    dataModule,
                    repositoryModule,
                    uiModule,
                    domainModule,
                    databaseModule,
                    networkModule,
                    firestoreModule,
                    widgetModule
                )
            )
        }

        val updateService: UpdateService by inject()

        applicationScope.launch(Dispatchers.IO) {
            updateService.getLatestVersion()
        }
        SaveNotificationWorker.testenqueueWork(this)
    }
}