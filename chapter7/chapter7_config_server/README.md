# Config Server

Config Server는 클라이언트 애플리케이션에 적용되는 설정을 저장하고 관리하는 역할을 합니다.

로컬또는 Git을 이용하여 설정을 할 수 있지만, Vault를 이용하여 환경 설정 정보를 가져오도록 변경했습니다.

`src/resources/bootstrap.yml` 파일을 수정하여 설정을 합니다.

``` yaml
spring:
  application:
    name: config-server
  profiles:
    active: vault
  cloud:
    config:
      server:
        vault:
          port: [사용 port]
          host: [host 주소]
          token: [토큰 값]
          kvVersion: 2
          backend: [서비스명]
          profile-separator: /


server:
  port: 8071

encrypt:
  key: fje83Ki8403Iod87dne7Yjsl3THueh48jfuO9j4U2hf64Lo

#Enable all Spring Boot Actuator endpoints.
management:
  endpoints:
    web:
      exposure:
        include: "*"

```
