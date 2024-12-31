plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.microservice"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-server
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:4.1.3")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter
    implementation("org.springframework.cloud:spring-cloud-starter:4.1.3")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.3.4")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-config
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.1.3")
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-loadbalancer
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer:4.1.3")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
