object Versions {
    const val androidCore = "1.10.0"
    const val androidAppCompat = "1.6.1"
    const val androidLifecycle = "2.6.1"
    const val androidMaterial = "1.8.0"

    const val workManager = "2.8.1"

    const val viewPager = "1.0.0"

    const val koin = "3.4.0"

    const val ktor = "2.2.2"

    const val kotlinSerialization = "1.4.1"
    const val kotlinCoroutines = "1.6.4"

    const val sqlDelight = "1.5.5"

    // Jetpack Compose
    const val compose = "1.5.0-alpha02"
    const val composeNavigation = "2.5.3"
    const val composeCompiler = "1.4.7"
    const val composeActivity = "1.7.1"
}

object Dependencies {
    // Android
    val androidCore by lazy { "androidx.core:core-ktx:${Versions.androidCore}" }
    val androidAppCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidAppCompat}" }
    val androidLifecycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidLifecycle}" }
    val androidMaterial by lazy { "com.google.android.material:material:${Versions.androidMaterial}" }
    val viewModelLifecycle by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycle}" }

    // Android ui
    val viewPager by lazy { "androidx.viewpager2:viewpager2:${Versions.viewPager}" }

    // Work Manager
    val workManager by lazy { "androidx.work:work-runtime-ktx:${Versions.workManager}" }

    // Koin
    val koinAndroid by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    val koinCore by lazy { "io.insert-koin:koin-core:${Versions.koin}" }
    val koinCompose by lazy { "io.insert-koin:koin-androidx-compose:${Versions.koin}" }

    // kotlinx
    val kotlinSerialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}" }
    val kotlinCoroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}" }

    // Ktor
    val ktorHttpCLient by lazy { "io.ktor:ktor-client-okhttp:${Versions.ktor}" }
    val ktorCore by lazy { "io.ktor:ktor-client-core:${Versions.ktor}" }

    // SqlDelight
    val sqlDelightDriver by lazy { "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}" }
    val sqlDelightRuntime by lazy { "com.squareup.sqldelight:runtime:${Versions.sqlDelight}" }
    val sqlDelightCoroutinesExtension by lazy { "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}" }

    // Jetpack Compose
    val composeNavigation by lazy { "androidx.navigation:navigation-compose:${Versions.composeNavigation}" }
    val composeCompiler by lazy { "androidx.compose.compiler:compiler:${Versions.composeCompiler}" }
    val composeActivity by lazy { "androidx.activity:activity-compose:${Versions.composeActivity}" }
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }
}
