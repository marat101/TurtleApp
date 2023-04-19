plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.turtleteam.widget_schedule"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

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
    implementation(project(":domain"))

    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // ViewPager
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Koin
    val koin = "3.2.2"
    implementation("io.insert-koin:koin-core:$koin")

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
}