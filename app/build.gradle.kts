plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
//    id ("androidx.navigation.safeargs")
}

android {
    namespace ="com.chacha.booking"
    compileSdk =33

    defaultConfig {
        applicationId= "com.chacha.booking"
        minSdk =21
        targetSdk =33
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
        sourceCompatibility =JavaVersion.VERSION_11
        targetCompatibility =JavaVersion.VERSION_11
    }


    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs + "-Xjvm-default=all"

    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes+= ("META-INF/INDEX.LIST")
            pickFirsts.add("META-INF/io.netty.versions.properties")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion ="1.4.0"
    }

    buildFeatures {
        viewBinding =true
        compose =true
    }

}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))


    implementation(libs.android.coreKtx)
    implementation(libs.android.appCompat)
    implementation(libs.android.material)
    implementation(libs.biometric)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycle.runtimeKtx)
    implementation(libs.timber)
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
/*


    implementation ("androidx.core:core-ktx:1.9.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0-alpha02")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation ("com.google.firebase:firebase-auth-ktx:21.1.0")
    implementation ("com.google.firebase:firebase-auth:21.1.0")
    implementation ("com.google.firebase:firebase-analytics-ktx:21.2.0")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    //Calendar
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.0.2")
    implementation ("com.maxkeppeler.sheets-compose-dialogs:calendar:1.0.2")

    //viewpager2
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    //indicator
    implementation ("me.relex:circleindicator:2.1.6")
    //lottie
    implementation ("com.airbnb.android:lottie:5.0.3")
    implementation("io.github.amrdeveloper:lottiedialog:1.0.0")

    // SplashScreen Api
    implementation ("androidx.core:core-splashscreen:1.0.0")
    implementation ("com.google.guava:guava:30.1-jre")

    // Accompanist - ViewPager
    implementation ("com.google.accompanist:accompanist-pager:0.23.1")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.23.1")

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.5.3")

    // Saved state module for ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")*/

}