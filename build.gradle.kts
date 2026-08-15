plugins {
    id("java")
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


}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.register<JavaExec>("codegenLuminor") {
    group = "playwright"
    description = "Record Luminor UI test with Playwright Codegen"

    classpath = sourceSets["test"].runtimeClasspath
    mainClass.set("com.microsoft.playwright.CLI")

    args(
        "codegen",
        "--browser=chromium",
        "https://luminor.lv/en"
    )
}
