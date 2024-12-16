import java.text.SimpleDateFormat
import java.util.Date

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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "baseUrl", "\"https://api-dev.xdaoebike.com:32001/\"")
            versionNameSuffix = "-debug" + "-" + loadConfig() + "-" + getGitCommitCount()
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
    android.applicationVariants.all {
        // 编译类型
        val buildType = this.buildType.name
        val date = SimpleDateFormat("yyyy-MM-dd-HH-mm").format(Date())
        outputs.all {
            // 判断是否是输出 apk 类型
            if (this is com.android.build.gradle
                .internal.api.ApkVariantOutputImpl
            ) {
                this.outputFileName = "smartbike" +
                        "_${android.defaultConfig.versionName}_${date}_${buildType}.apk"
            }
        }
    }
}

fun getGitCommitCount(): String {//获取git提交次数
    val os = org.apache.commons.io.output.ByteArrayOutputStream()
    project.exec {
        commandLine = "git rev-list --count HEAD".split(" ")
        standardOutput = os
    }
    return String(os.toByteArray()).trim()
}

fun loadConfig(): String {//获取当前分支名称
    val config = file("${project.projectDir.parent}/.git/HEAD")
    var content = ""
    config.forEachLine { line ->
        content = line
    }
    var split = content.split("/")
    return split[split.size - 1]
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