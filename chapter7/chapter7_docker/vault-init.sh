#!/bin/sh

# Vault 초기화 (Vault가 준비될 때까지 대기)
echo "Waiting for Vault to be ready..."
until curl -s http://127.0.0.1:8200/v1/sys/health | grep "initialized"; do
  sleep 1
done

# Vault 환경변수 설정
export VAULT_ADDR='http://127.0.0.1:8200'
vault login root

# 시크릿 엔진 활성화 (Key-Value v2 시크릿 엔진)
vault secrets enable -path=secret kv-v2

# 초기 시크릿 데이터 추가
vault kv put secret/config-server username="configUser" password="configPassword"
vault kv put secret/backend-service db_username="dbUser" db_password="dbPassword"

echo "Vault initialization complete."
