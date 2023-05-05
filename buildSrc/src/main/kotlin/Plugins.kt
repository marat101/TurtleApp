object PluginVersion {
    const val gradle = "7.4.2"
    const val googleServices = "4.3.15"

    const val kotlin = "1.8.21"

    const val androidApp = "7.4.0"
}

object Plugins {
    val gradle by lazy { "com.android.tools.build:gradle:${PluginVersion.gradle}"}
    val sqlDelight by lazy { "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}" }
    val googleServices by lazy { "com.google.gms:google-services:${PluginVersion.googleServices}" }
}