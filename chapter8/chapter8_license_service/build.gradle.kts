plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "com.mircoservice"
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
    // Spring Cloud bootstrap 설정 필요
    // Config 서버와 같은 외부 설정을 초기화 단계에서 처리
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

    // Spring MVC를 사용하여 REST API 개발
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Data JPA를 사용한 데이터베이스 연동
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Kotlin 리플렉션 사용 (Spring DI 및 런타임 관련 기능)
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Cloud Config 클라이언트: Config 서버에서 설정을 가져옴
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    // PostgreSQL 데이터베이스 드라이버
    runtimeOnly("org.postgresql:postgresql")

    // Spring Boot 테스트 라이브러리
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Kotlin JUnit5 지원 테스트 라이브러리
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    // JUnit 플랫폼 런처 (테스트 실행 시 필요)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // HATEOAS: REST API에서 하이퍼미디어 기능 지원
    implementation("org.springframework.hateoas:spring-hateoas:2.3.3")

    // Spring Actuator: 애플리케이션의 모니터링 및 관리 기능 제공
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.3.4")

    // Resilience4j: 서킷 브레이커 및 타임리미터를 통한 장애 복원력 관리
    implementation("io.github.resilience4j:resilience4j-spring-boot2:2.2.0")
    implementation("io.github.resilience4j:resilience4j-circuitbreaker:2.2.0")
    implementation("io.github.resilience4j:resilience4j-timelimiter:2.2.0")

    // Spring AOP: 애플리케이션의 단면(Aspect) 기능 제공
    implementation("org.springframework:spring-aop:6.1.12")

    // Netflix Eureka 클라이언트: Eureka 서버와 통신하여 서비스 디스커버리 지원
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.1.3")

    // Spring Cloud LoadBalancer: 로드밸런싱 기능 제공
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer:4.1.3")

    // Spring Cloud OpenFeign: 선언적 REST 클라이언트 지원
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.3")

    // Spring Cloud Config 클라이언트: 외부 설정 관리 지원
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.1.3")

    // Kotlin 로깅 관리 라이브러리
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

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
