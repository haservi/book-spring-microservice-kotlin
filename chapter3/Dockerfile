# 기본 이미지로 OpenJDK 21 사용
FROM openjdk:21-slim

# 작업 디렉토리 생성
WORKDIR /app

COPY . .

# JAR 파일을 실행하도록 설정
CMD ["java", "-jar", "build/libs/chapter3_license-0.0.1-SNAPSHOT.jar"]