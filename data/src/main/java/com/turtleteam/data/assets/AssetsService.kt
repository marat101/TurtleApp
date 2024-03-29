package com.android.turtleapp.data.local.assets

import android.content.Context
import android.content.res.AssetManager
import com.turtleteam.domain.model.callschedule.Calls
import com.turtleteam.domain.repository.Assets
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AssetsService(private val context: Context) : Assets {

    override fun getCallsSchedule(): ArrayList<Calls> {
        return Json.decodeFromString(
            context.assets.readAssetsFile("callschedule.json")
        )
    }
}

private fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }
