plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.17.0")

    implementation("commons-io:commons-io:2.15.0")

    implementation("org.apache.logging.log4j:log4j-api:2.22.0")
    implementation("org.apache.logging.log4j:log4j-core:2.22.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    reports {
        junitXml.required.set(true)
        junitXml.outputLocation = layout.projectDirectory.dir("test-reports/xml/")
        // Gradle build-in HTML reports
        html.required.set(true)
        html.outputLocation = layout.projectDirectory.dir("test-reports/html/")
    }
}