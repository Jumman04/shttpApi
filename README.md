# shttp

semple http call

## To integrate Jummania-Slider into your Android project, follow these steps:

### Step 1: Add JitPack Repository

Add the JitPack repository to your project's root `settings.gradle` file:

```ktx
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

## Step 2: Add the Dependency

Add the Jummania-Slider dependency to your app module's `build.gradle` file:

 ```gradle
dependencies {
    implementation("com.github.Mdsarowarhossain:shttp:1.0")
}
```

##jitpack
[![](https://jitpack.io/v/Mdsarowarhossain/shttp.svg)](https://jitpack.io/#Mdsarowarhossain/shttp)
