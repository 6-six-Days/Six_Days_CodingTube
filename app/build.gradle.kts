plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
}



android {
    namespace = "com.example.bluecodingtube"
    compileSdk = 33




    defaultConfig {
        applicationId = "com.example.bluecodingtube"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // viewPager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // api 통신
    implementation ("com.google.api-client:google-api-client:1.32.1")
    implementation ("com.google.oauth-client:google-oauth-client-jetty:1.23.0")
//implementation 'com.google.http-client:google-http-client-jackson2:1.39.2'

    implementation ("com.google.apis:google-api-services-youtubeAnalytics:v2-rev16-1.23.0")
    implementation ("com.google.apis:google-api-services-youtube:v3-rev20210915-1.32.1")
    implementation ("com.google.http-client:google-http-client-android:1.34.2")


    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // Converter ( JSON 타입 결과를 객체로 매핑 )
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // okhttp3
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.0")

    implementation ("com.localebro:okhttpprofiler:1.0.8")

    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.1")


}