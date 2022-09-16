buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    val sqlDelightVersion = "1.5.2"

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20-RC")
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
