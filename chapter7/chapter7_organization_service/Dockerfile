FROM gradle:jdk21-alpine AS build

# 애플리케이션 코드가 들어갈 작업 디렉토리 설정
WORKDIR /app

# Gradle 캐시를 활용하기 위해 build.gradle과 settings.gradle을 먼저 복사
COPY build.gradle.kts settings.gradle.kts ./

# 의존성만 먼저 다운로드
RUN gradle dependencies --no-daemon

# 나머지 프로젝트 파일 복사
COPY . .

# 프로젝트 빌드 (jar 파일 생성)
RUN gradle clean build --no-daemon

# 2. Run Stage (최종 이미지 - OpenJDK로 애플리케이션 실행)
FROM openjdk:21-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 단계에서 생성된 jar 파일을 복사
COPY --from=build /app/build/libs/*.jar /app/app.jar

# 컨테이너 시작 시 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]