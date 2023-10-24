plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.chacha.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

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
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    hilt {
        enableExperimentalClasspathAggregation = true
    }

}

dependencies {

    implementation(project(":domain"))
    implementation(platform(libs.compose.bom))
    implementation(libs.date.core)
    implementation(libs.date.sheet.dialog)
    implementation(libs.vanpra.date.sheet.dialog)
    implementation(libs.compose.constraintlayout)
    implementation(libs.datastore)
    implementation(libs.coil.gf)
    implementation(libs.android.appCompat)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.lifecycle.runtimeKtx)
    implementation(libs.timber)
    implementation(libs.bundles.retrofit)
    implementation(libs.android.hilt)
    implementation(libs.androidx.splashscreen)
    implementation(libs.kotlin.coroutines.play.services)
    implementation(libs.gms.play.services.auth)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.lottie.compose)
    implementation(libs.gson.gson)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.kotlin.coroutines.datetime)
    implementation(libs.zeko.query.builder)
    kapt(libs.android.hilt.compiler)
    implementation(libs.android.hilt.navigation.compose)
    kapt(libs.android.hilt.androidx.compiler)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.android.test.espresso)
    testImplementation(libs.test.junit4)
    testImplementation(libs.test.robolectric)
    testImplementation(libs.android.test.espresso)
    testImplementation(libs.test.navigation)
    testImplementation(libs.test.mockk)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.kizitonwose.calender)


}
kapt {
    correctErrorTypes = true
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
