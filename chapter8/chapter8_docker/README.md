docker build -f Dockerfile.Vault -t vault-image .

docker run -d --name vault-container -p 8200:8200 vault-image

## 도커 컴포즈

```yaml
docker-compose up
```

```yaml
docker-compose down --rmi all
```

### Valut에 config 설정

```yaml
{
  "spring": {
    "application": {
      "name": "eureka-server"
    },
    "boot": {
      "admin": {
        "context-path": "/admin"
      }
    }
  },
  "server": {
    "port": 8070
  },
  "eureka": {
    "instance": {
      "hostname": "eureka-server"
    },
    "client": {
      "registerWithEureka": false,
      "fetchRegistry": false,
      "serviceUrl": {
        "defaultZone": "http://${eureka.instance.hostname}:${server.port}/eureka/"
      }
    },
    "server": {
      "waitTimeInMsWhenSyncEmpty": 5
    }
  },
  "management": {
    "endpoints": {
      "web": {
        "exposure": {
          "include": "*"
        }
      }
    }
  }
}

```

### licensing-service 설정

```yaml
{
  "example": {
    "property": "licensing service property default"
  },
  "spring": {
    "jpa": {
      "hibernate": {
        "ddl-auto": "none",
        "naming-strategy": "org.hibernate.cfg.ImprovedNamingStrategy"
      },
      "database": "POSTGRESQL",
      "show-sql": true,
      "properties": {
        "hibernate": {
          "dialect": "org.hibernate.dialect.PostgreSQLDialect"
        }
      }
    },
    "datasource": {
      "platform": "postgres",
      "testWhileIdle": true,
      "validationQuery": "SELECT 1"
    },
    "database": {
      "driverClassName": "org.postgresql.Driver"
    },
    "cloud": {
      "loadbalancer": {
        "ribbon": {
          "enabled": false
        }
      }
    }
  },
  "management": {
    "endpoints": {
      "web": {
        "exposure": {
          "include": "*"
        }
      },
      "enabled-by-default": true,
      "endpoint": {
        "health": {
          "enabled": true,
          "show-details": "always"
        }
      }
    }
  },
  "eureka": {
    "instance": {
      "preferIpAddress": true
    },
    "client": {
      "registerWithEureka": true,
      "fetchRegistry": true,
      "serviceUrl": {
        "defaultZone": "http://localhost:8070/eureka/"
      }
    }
  }
}
```

### organization-service 설정

```yaml
{
  "example": {
    "property": "organization service property default"
  },
  "spring": {
    "jpa": {
      "hibernate": {
        "ddl-auto": "none",
        "naming-strategy": "org.hibernate.cfg.ImprovedNamingStrategy"
      },
      "database": "POSTGRESQL",
      "show-sql": true,
      "properties": {
        "hibernate": {
          "dialect": "org.hibernate.dialect.PostgreSQLDialect"
        }
      }
    },
    "datasource": {
      "platform": "postgres",
      "testWhileIdle": true,
      "validationQuery": "SELECT 1"
    },
    "database": {
      "driverClassName": "org.postgresql.Driver"
    },
    "cloud": {
      "loadbalancer": {
        "ribbon": {
          "enabled": false
        }
      }
    }
  },
  "management": {
    "endpoints": {
      "web": {
        "exposure": {
          "include": "*"
        }
      },
      "enabled-by-default": true,
      "endpoint": {
        "health": {
          "enabled": true,
          "show-details": "always"
        }
      }
    }
  },
  "eureka": {
    "instance": {
      "preferIpAddress": true
    },
    "client": {
      "registerWithEureka": true,
      "fetchRegistry": true,
      "serviceUrl": {
        "defaultZone": "http://localhost:8070/eureka/"
      }
    }
  }
}
```