# 빌드 스테이지
FROM gradle:jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# 실행 스테이지
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/chapter5_configserver.jar
CMD ["java", "-jar", "chapter5_configserver.jar"]