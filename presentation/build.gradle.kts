import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.chacha.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs + "-Xjvm-default=all"
    }

    buildFeatures {
        compose = true
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes+= ("META-INF/INDEX.LIST")
            pickFirsts.add("META-INF/io.netty.versions.properties")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(libs.bundles.compose)
    implementation(libs.compose.constraintlayout)


    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.runtime:runtime:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.foundation:foundation-layout:1.4.3")
    implementation("androidx.compose.ui:ui-util:1.4.3")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.1")
    implementation("androidx.compose.animation:animation:1.4.3")
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.25.1")
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("androidx.room:room-paging:2.5.2")
    implementation("com.google.dagger:dagger:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:hilt-android:2.46.1")
    implementation("androidx.glance:glance-appwidget:1.0.0-beta01")
    debugImplementation("com.google.android.glance.tools:appwidget-viewer:0.2.2")
    implementation("org.apache.commons:commons-csv:1.10.0")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    androidTestImplementation("androidx.compose.ui:ui-test:1.4.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.46.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.46.1")

    implementation(libs.androidx.splashscreen)
    implementation(libs.kotlin.coroutines.play.services)
    implementation(libs.gms.play.services.auth)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.lottie.compose)
    implementation(libs.gson.gson)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.kotlin.coroutines.datetime)
    implementation(libs.zeko.query.builder)

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation ("com.google.accompanist:accompanist-pager:0.28.0")
    implementation  ("com.google.accompanist:accompanist-pager-indicators:0.28.0")
    implementation  ("com.google.accompanist:accompanist-navigation-material:0.28.0")
    implementation  ("com.google.accompanist:accompanist-navigation-animation:0.28.0")
    implementation ("com.google.accompanist:accompanist-permissions:0.25.1")
    implementation ("androidx.biometric:biometric:1.1.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")


    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.8.1-rc")
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:1.1.6")
    implementation ("com.google.android.material:material:1.7.0-beta01")





}

kotlin {
    sourceSets {
        all {
            languageSettings.apply {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }
    }
}
