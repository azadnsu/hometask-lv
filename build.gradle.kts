plugins {
    id("java")
    id("io.qameta.allure") version "4.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // RestAssured
    testImplementation("io.rest-assured:rest-assured:5.5.6")

    // Jackson - required for serializing HashMap/object bodies
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")

    // Source: https://mvnrepository.com/artifact/com.microsoft.playwright/playwright
    implementation("com.microsoft.playwright:playwright:1.62.0")

    //Allure for RestAssured
    testImplementation("io.qameta.allure:allure-rest-assured:2.35.2")


}
allure {
    version.set("2.45.0") // pin to Allure 2 report (Allure 3 is the plugin's default)
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.register<JavaExec>("playwrightInstall") {
    group = "playwright"
    description = "Install Playwright browsers"

    classpath = sourceSets["test"].runtimeClasspath
    mainClass.set("com.microsoft.playwright.CLI")

    args("install", "chromium")
}

