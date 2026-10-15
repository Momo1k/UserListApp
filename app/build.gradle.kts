plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.userlistapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.userlistapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")
}