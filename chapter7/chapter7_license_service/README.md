## 일반 도커

```
docker build -t chapter7:latest -f Dockerfile .

docker run -d --rm -p 8080:8080 chapter3:latest
```

java -Dspring.cloud.config.uri=http://localhost:8071 -Dspring.profiles.active=dev -jar
./chapter7_license_service-0.0.1-SNAPSHOT

```commandline
docker build -f Dockerfile.Vault -t vault-image .

docker run -d --name vault-container -p 8200:8200 vault-image

```