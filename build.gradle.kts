plugins {
    id("java")
}

group = "com.spring.mvc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.telegram/telegrambots
    implementation(group = "org.telegram", name = "telegrambots", version = "6.7.0")
    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    implementation (group= "org.jsoup", name= "jsoup", version= "1.15.3")
// https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation (group= "com.google.code.gson", name= "gson", version= "2.10.1")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly (group= "org.projectlombok", name= "lombok", version= "1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    // https://mvnrepository.com/artifact/org.telegram/telegrambotsextensions
    implementation (group= "org.telegram", name= "telegrambotsextensions", version= "6.1.0")


}

tasks.test {
    useJUnitPlatform()
}
