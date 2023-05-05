plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlinx-serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {

    implementation(Dependencies.koinCore)
    implementation(Dependencies.kotlinCoroutines)
    implementation(Dependencies.kotlinSerialization)
}