plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

android {
    namespace = "com.turtleteam.turtle_database"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.test.ext:junit-ktx:1.1.5")
    // Koin
    val koin = "3.2.2"
    implementation("io.insert-koin:koin-android:$koin")

    // SQLdelight
    val sqlDelightVersion = "1.5.5"
    implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
    implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
    implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")

    //kotlinx
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("com.squareup.sqldelight:sqlite-driver:1.4.4")
    testImplementation("org.robolectric:robolectric:4.10-alpha-1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

sqldelight {
    database("TurtleDatabase") {
        packageName = "com.turtleteam.turtle_database"
    }
}