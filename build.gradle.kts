buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.6.21-1.0.6")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
