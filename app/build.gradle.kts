plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.turtleteam.turtleappcompose"
    compileSdk = Config.compileSdk

    signingConfigs {
        create("release") {
            keyAlias = "turtle"
            keyPassword = "sjdhfksdjskdfj"
            storeFile = file("turtlekeys.jks")
            storePassword = "sjdhfksdjskdfj"
        }
    }

    defaultConfig {
        applicationId = "com.android.turtleapp"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("release")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("com.google.firebase:firebase-analytics-ktx:21.2.2")
    implementation("com.google.firebase:firebase-messaging-ktx:23.1.2")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.3.7")
    implementation("com.google.firebase:firebase-messaging-directboot:23.1.2")

    implementation(Dependencies.koinAndroid)
    implementation(project(Modules.ui))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.turtleDatabase))
    implementation(project(Modules.ktorClient))
    implementation(project(Modules.widgetSchedule))
    implementation(project(Modules.remoteDatabase))
    implementation(project(Modules.notifications))
}