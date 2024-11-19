plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)

    jacoco
}

android {
    namespace = "com.example.learning"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.learning"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/LICENSE-notice.md"
        }

    }
    lint {
        htmlReport = true
        xmlReport = true
        htmlOutput = file("$buildDir/reports/lint-results.html")
        xmlOutput = file("$buildDir/reports/lint-results.xml")
    }
}
jacoco {
    // Optionally, set the tool version for tasks if needed
    toolVersion = "0.8.10"
}
tasks.register<JacocoReport>("advancedTestReport") {
    dependsOn("testDebugUnitTest") // Run both unit and UI tests

    reports {
        xml.required.set(true)  // Enable XML report (for CI integration)
        html.required.set(true) // Enable HTML report
        csv.required.set(false) // Optional: Enable CSV report if needed
    }

    executionData.setFrom(fileTree(buildDir) {
        include(
            "jacoco/testDebugUnitTest.exec", // Unit test coverage
            "outputs/code_coverage/debugAndroidTest/connected/**/*.ec" // Instrumentation coverage
        )
    })

    classDirectories.setFrom(fileTree("$buildDir/intermediates/classes/debug") {
        exclude(
            "**/R.class",
            "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*",
            "android/**/*.*"
        )
    })

    sourceDirectories.setFrom(
        files("src/main/java", "src/main/kotlin")
    )
}


dependencies {
    // -------------------- Core Android Libraries --------------------
    implementation(libs.androidx.core.ktx)                   // Core KTX extensions
    implementation(libs.androidx.lifecycle.runtime.ktx)      // Lifecycle KTX
    implementation(libs.androidx.activity.compose)           // Activity Compose support
    implementation(platform(libs.androidx.compose.bom))      // Compose BOM
    implementation(libs.androidx.ui)                         // Compose UI
    implementation(libs.androidx.ui.graphics)                // Graphics
    implementation(libs.androidx.ui.tooling.preview)          // Preview tooling
    implementation(libs.androidx.material3)                   // Material 3

    // -------------------- Networking Libraries --------------------
    implementation(libs.retrofit)                             // Retrofit for API calls
    implementation(libs.converter.gson)                       // Gson converter for Retrofit
    implementation(libs.kotlinx.coroutines.android)           // Coroutine support
    implementation(libs.coil.compose)                         // Coil for image loading in Compose
    implementation(libs.logging.interceptor)                  // Logging interceptor for Retrofit

    // -------------------- Dependency Injection --------------------
    implementation(libs.hilt.android)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.testing)
    implementation(libs.firebase.crashlytics.buildtools)                         // Hilt Android
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)            // Hilt Android support for tests
    ksp(libs.hilt.compiler)                                  // Hilt compiler (using KSP)
    implementation(libs.androidx.hilt.navigation.compose)    // Hilt navigation support for Compose

    // -------------------- Testing Libraries --------------------
    // Unit Testing
    testImplementation(libs.mockk)                            // MockK for unit tests
    androidTestImplementation(libs.mockkAndroid)             // MockK for unit tests

    // Android Instrumentation Tests
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Compose BOM for Android tests
    androidTestImplementation(libs.androidx.ui.test.junit4)  // UI testing for Compose
    androidTestImplementation(libs.androidx.ui.test.manifest)        // Manifest for Compose UI tests

    // -------------------- Debugging Tools --------------------
    debugImplementation(libs.androidx.ui.tooling)           // Tooling support for Compose in debug mode
    debugImplementation(libs.androidx.ui.test.manifest)     // Manifest testing support for Compose
}
