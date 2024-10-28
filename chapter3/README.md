## 일반 도커

```
docker build -f Dockerfile -t chapter3-docker:latest .

docker run -d --rm -p 8080:8080 chapter3-docker:latest
```

## 멀티스테이지 도커

```
docker build -f Dockerfile.multi -t chapter3-multi-docker:latest .

docker run -d --rm -p 8080:8080 chapter3-multi-docker:latest
```

## 스프링부트 빌드팩

```
./gradlew bootBuildImage --imageName=chapter3-buildpack:latest

docker run -d --rm -p 8080:8080 chapter3-buildpack:latest
```

## 도커 컴포즈

```
docker-compose up --build

docker-compose down --rmi all
```