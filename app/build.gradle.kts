plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.diffplug.spotless") version "6.2.1"
}

android {
    namespace = "com.anne.play"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.anne.play"
        minSdk = 24
        targetSdk = 33
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
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
spotless {
    java {
        googleJavaFormat()
    }
}

dependencies {
    val composeVersion = rootProject.extra["composeVersion"] as String?
    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.2")
    implementation("dev.chrisbanes.accompanist:accompanist-insets:0.6.2")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Compose依赖库
    implementation("androidx.compose.ui:ui:$composeVersion")
//    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material:1.5.4")

    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")

    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")

    val accompanistVersion = "0.32.0"
    implementation("com.google.accompanist:accompanist-webview:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")

    val glanceVersion = "1.0.0"
    implementation("androidx.glance:glance:$glanceVersion")
    implementation("androidx.glance:glance-appwidget:$glanceVersion")
    implementation("androidx.glance:glance-material3:$glanceVersion")

    // Paging loading
    implementation("androidx.paging:paging-compose:3.2.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0")

    // material
//    implementation("androidx.compose.material:material:1.5.1")
//    implementation("androidx.compose.material3:material3:1.0.0-alpha09"

//    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.2")
//    implementation("dev.chrisbanes.accompanist:accompanist-insets:0.6.2")
    // network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.2")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0-rc02")
    // Compose banner 滚动
//    implementation("com.github.zhujiang521:Banner:1.3.6")
    implementation("com.github.zhujiang521:Banner:1.5.3")

//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
}
