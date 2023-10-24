plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
    id("com.google.firebase.crashlytics")
    id("com.google.devtools.ksp")
    id("com.google.firebase.firebase-perf")
}

android {
    namespace ="com.chacha.booking"
    compileSdk =34

    defaultConfig {
        applicationId= "com.chacha.booking"
        minSdk =24
        targetSdk =34
        versionCode= 1
        versionName= "1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
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
    compileOptions {
//        coreLibraryDesugaringEnabled = true
        sourceCompatibility =JavaVersion.VERSION_17
        targetCompatibility =JavaVersion.VERSION_17
    }


    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs + "-Xjvm-default=all"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes+= ("META-INF/INDEX.LIST")
            pickFirsts.add("META-INF/io.netty.versions.properties")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    buildFeatures {
        compose =true
    }
    hilt {
        enableExperimentalClasspathAggregation = true
    }


}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(platform(libs.compose.bom))
    implementation(libs.android.appCompat)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycle.runtimeKtx)
    implementation(libs.timber)
    implementation(libs.android.hilt)
    implementation(libs.androidx.splashscreen)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.kotlin.coroutines.datetime)
    implementation(libs.zeko.query.builder)
    kapt(libs.android.hilt.compiler)
    implementation(libs.android.hilt.navigation.compose)
    kapt(libs.android.hilt.androidx.compiler)
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.test.manifest)




}

kapt {
    correctErrorTypes = true
}