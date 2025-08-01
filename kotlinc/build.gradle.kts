plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.android")
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "org.jetbrains.kotlin"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation("org.lsposed.hiddenapibypass:hiddenapibypass:6.1")
    implementation("org.codehaus.woodstox:stax2-api:4.2.2")

    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.21")
    implementation("io.github.itsaky:nb-javac-android:17.0.0.3")
    implementation("org.jetbrains.intellij.deps:trove4j:1.0.20200330")
    implementation("org.jdom:jdom:2.0.2")

    implementation(projects.jaxp)
    api(files("libs/kotlin-compiler-2.0.0.jar"))

    compileOnly(projects.theUnsafe)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.cosmic.ide"
            artifactId = "kotlinc-android"
            version = "2.0.0"

            pom {
                name.set("Kotlin Compiler")
                description.set("A port of the Kotlin Compiler for the Android platform.")
                url.set("http://github.com/Cosmic-IDE/kotlinc")
                licenses {
                    license {
                        name.set("GNU GPL v3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }
                developers {
                    developer {
                        id.set("pranavpurwar")
                        name.set("Pranav Purwar")
                        email.set("purwarpranav80@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Cosmic-IDE/kotlinc.git")
                    developerConnection.set("scm:git:ssh://github.com/Cosmic-IDE/kotlinc.git")
                    url.set("http://github.com/Cosmic-IDE/kotlinc")
                }
            }

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

