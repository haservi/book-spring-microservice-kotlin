# 빌드 스테이지
FROM gradle:jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# 실행 스테이지
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]