plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.bignerdranch.android.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        renderscriptTargetApi = 21
        renderscriptSupportModeEnabled = true
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
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {
    implementation (project(":domain"))
    implementation (project(":data"))

//        implementation ("com.github.Dimezis:BlurView:version-2.0.6")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.ui.text.google.fonts)

//    implementation (libs.blurview)
    implementation ("com.hannesdorfmann:adapterdelegates4:4.3.0")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")

    implementation("androidx.compose.ui:ui:1.7.8")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.8")
    implementation("androidx.compose.foundation:foundation:1.8.0-rc01")

    // Compose
    implementation ("androidx.activity:activity-compose:1.7.2")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    // Material Components
//    implementation ("com.google.android.material:material:1.9.0")

    // Navigation Component
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    // Hilt
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    kapt ("com.google.dagger:hilt-compiler:2.51.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation ("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

}