pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            setUrl("https://repo1.maven.org/maven2/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/google/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/jcenter/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/gradle-plugin/")
        }
        maven {
            setUrl("https://repo1.maven.org/maven2/")
        }

        maven { setUrl("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://repo1.maven.org/maven2/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/google/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/jcenter/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/gradle-plugin/")
        }
        maven {
            setUrl("https://repo1.maven.org/maven2/")
        }
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "XiaoDaoControl"
include(":app")
 