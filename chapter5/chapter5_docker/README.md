```bash
docker-compose -f 파일명.yml up
```

도커 compose 이미지 모두 삭제

```bash
docker-compose -f .\docker-compose-vault.yml down --rmi all
```

### 볼트 config 설정 테스트

```commandline
docker-compose -f 
```

```bash
curl -X "GET" "http://localhost:8071/licensing-service/default" -H "X-Config-Token:myroot"
```