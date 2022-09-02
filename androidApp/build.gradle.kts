plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.voicenotes.android"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    // Set both the Java and Kotlin compilers to target Java 8.
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val koin = "3.2.0"
val composeVersion = "1.2.0"

dependencies {
    implementation(project(":shared"))

    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    implementation("io.insert-koin:koin-core:$koin")
    implementation("io.insert-koin:koin-android:$koin")

    // Jetpack Compose
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.5.1")
    // Compose Material Design
    implementation("androidx.compose.material:material:$composeVersion")
    // Animations
    implementation("androidx.compose.animation:animation:$composeVersion")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
}
