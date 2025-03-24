plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.bignerdranch.android.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation (project(":domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.10.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

// Hilt
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    kapt ("com.google.dagger:hilt-compiler:2.51.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation ("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}