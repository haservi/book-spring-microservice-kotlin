## 일반 도커
```
docker build -t chapter3:latest -f Dockerfile .

docker run -d --rm -p 8080:8080 chapter3:latest
```

## 멀티스테이지 도커
```
docker build -t chapter3-multi:latest -f Dockerfile.multi .

docker run -d --rm -p  8080:8080 chapter3-multi:latest
```

## 스프링부트 빌드팩

```
./gradlew bootBuildImage --imageName=chapter3-buildpack:latest

docker run -d --rm -p 8080:8080 chapter3-buildpack:latest
```

## 도커 컴포즈

```
docker-compose up --build

docker-compose down
```