# # 기본 이미지로 OpenJDK 21 사용
# FROM openjdk:21-slim
#
# # 작업 디렉토리 생성
# WORKDIR /app
#
# # 소스 코드 복사
# COPY . .
#
# # 실행 권한 추가
# RUN chmod +x ./gradlew
#
# # Gradle을 사용해 프로젝트 빌드
# RUN ./gradlew build --no-daemon
#
# COPY /app/build/libs/*.jar /app/chapter5_license.jar
#
# # JAR 파일을 실행하도록 설정
# CMD ["java", "-jar", "chapter5_license.jar"]


# 기본 이미지로 OpenJDK 21 사용
FROM openjdk:21-slim

# 작업 디렉토리 생성
WORKDIR /app

# 소스 코드 복사
COPY . .

# 실행 권한 추가
RUN chmod +x ./gradlew

# Gradle을 사용해 프로젝트 빌드
RUN ./gradlew clean build

COPY /app/build/libs/*.jar /app/chapter5_license.jar

# JAR 파일을 실행하도록 설정
CMD ["java", "-jar", "chapter5_license.jar"]