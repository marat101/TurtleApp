package com.android.turtleapp.data.local.assets

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.AssetManager
import com.android.turtleapp.data.model.callschedule.Calls
import com.android.turtleapp.data.model.teachers.Teachers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AssetsService(private val context: Context) {

    fun getJsonFromAssets(): Teachers? {
        return Json.decodeFromString(
            context.assets.readAssetsFile("fullname.json")
        )
    }

    fun getCallsSchedule(): Calls {
        return Json.decodeFromString(
            context.assets.readAssetsFile("callschedule.json")
        )
    }

    fun copyName(clipData: ClipData) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(clipData)
    }

    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }
}