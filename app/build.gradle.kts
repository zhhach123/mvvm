plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.xdaoebike.smartbike"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.xdaoebike.smartbike"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField("String", "baseUrl", "\"https://api-dev.xdaoebike.com:32001/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "baseUrl", "\"https://api-dev.xdaoebike.com:32001/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    api(libs.glide)
    api(libs.persistentcookiejar)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)
    api(libs.gson)
    annotationProcessor(libs.compiler)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.immersionbar)
    implementation(libs.immersionbar.ktx)
    implementation(libs.core)
    implementation(libs.com.afollestad.material.dialogs.lifecycle)
    implementation(libs.bottomsheets)
}