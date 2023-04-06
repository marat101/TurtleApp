package com.turtleteam.turtleappcompose

import android.app.Application
import com.turtleteam.ktor_client.di.networkModule
import com.turtleteam.remote_database.firestoreModule
import com.turtleteam.turtle_database.di.databaseModule
import com.turtleteam.data.di.dataModule
import com.turtleteam.remote_database.UpdateService
import com.turtleteam.turtleappcompose.di.domainModule
import com.turtleteam.turtleappcompose.di.repositoryModule
import com.turtleteam.ui.di.uiModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TurtleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TurtleApp)
            modules(
                listOf(
                    dataModule,
                    repositoryModule,
                    uiModule,
                    domainModule,
                    databaseModule,
                    networkModule,
                    firestoreModule
                )
            )
        }
    }
}