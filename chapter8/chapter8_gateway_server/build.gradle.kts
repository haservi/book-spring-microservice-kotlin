plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.0"
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

extra["springCloudVersion"] = "2024.0.0"

dependencies {
    // Spring Boot Actuator: 헬스체크, refresh, 메트릭스 등을 위한 의존성
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // Kotlin Reflect: 코틀린 리플렉션 기능을 사용하기 위한 의존성
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // Spring Cloud Config Client: 외부 설정 서버에서 구성 정보를 가져오는 클라이언트 의존성
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    // Spring Cloud bootstrap 명시 필요
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

    // Spring Cloud Gateway
    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-gateway
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.1.5")

    // Netflix Eureka Client: Eureka 서버와 통신하여 서비스 디스커버리를 제공
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    // Spring Boot Test: 스프링 부트 애플리케이션 테스트를 위한 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Kotlin Test for JUnit 5: 코틀린 기반의 테스트를 JUnit5와 통합하여 실행
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    // JUnit Platform Launcher: JUnit 플랫폼에서 테스트 실행을 위한 런처 의존성
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Kotlin Logging: 코틀린에서 로그 관리 및 로깅 처리를 위한 라이브러리
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
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
