plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5" // 최신 버전 기준
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm")
}

group = "seungkyu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jta-atomikos:2.7.18")
    compileOnly("org.springframework.boot:spring-boot-starter-mail")
    testImplementation("com.sun.mail:jakarta.mail:2.0.1")


    implementation("mysql:mysql-connector-java:8.0.33")

    // Lombok 추가
    implementation("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation("org.junit.platform:junit-platform-launcher:1.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core:5.18.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.18.0")

    // 테스트 시에도 Lombok annotation processing 사용
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
    implementation(kotlin("stdlib-jdk8"))

    val jacksonVersion = "2.15.3"

    implementation("com.h2database:h2:2.3.232")
    testImplementation("com.h2database:h2")

    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}