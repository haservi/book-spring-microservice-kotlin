## 일반 도커

```
docker build -t chapter5:latest -f Dockerfile .

docker run -d --rm -p 8080:8080 chapter3:latest
```

java -Dspring.cloud.config.uri=http://localhost:8071 -Dspring.profiles.active=dev -jar ./chapter5_license-0.0.1-SNAPSHOT