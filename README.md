# x-cv

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/x-cv-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and
  Jakarta Persistence
- YAML Configuration ([guide](https://quarkus.io/guides/config-yaml)): Use YAML to configure your Quarkus application
- SmallRye JWT ([guide](https://quarkus.io/guides/security-jwt)): Secure your applications with JSON Web Token
- Redis Cache ([guide](https://quarkus.io/guides/cache-redis-reference)): Use Redis as the caching backend
- Cache ([guide](https://quarkus.io/guides/cache)): Enable application data caching in CDI beans
- Kubernetes ([guide](https://quarkus.io/guides/kubernetes)): Generate Kubernetes resources from annotations
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### YAML Config

Configure your application with YAML

[Related guide section...](https://quarkus.io/guides/config-reference#configuration-examples)

The Quarkus application configuration is located in `src/main/resources/application.yml`.

### gRPC

Create your first gRPC service

[Related guide section...](https://quarkus.io/guides/grpc-getting-started)

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

```angular2html
my-quarkus-grpc-service/
├── pom.xml                          ← Quarkus BOM + plugin
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/company/myapp/
│   │   │       ├── api/                      ← Proto + generated code (git ignore)
│   │   │   │       │   └── grpc/
│   │   │       │       ├── MyServiceGrpc.java
│   │   │       │       └── proto/
│   │   │       │           └── my-service.proto
│   │   │       ├── application/              ← Config, exception mapper, health
│   │   │       │   ├── config/
│   │   │       │   ├── exception/
│   │   │       │   └── health/
│   │   │       ├── domain/                   ← Entity thuần (JPA + Panache)
│   │   │       │   ├── model/
│   │   │         │   └── User.java
│   │   │       │   └── repository/
│   │   │       │       └── UserRepository.java
│   │   │       ├── usecase/                  ← Business logic (port)
│   │   │       │   ├── port/
│   │   │       │   │   └── UserServicePort.java
│   │   │       │   └── impl/
│   │   │       │       └── UserServiceImpl.java
│   │   │       ├── adapter/
│   │   │       │   ├── grpc/                 ← gRPC implementation (adapter)
│   │   │       │   │   └── MyServiceGrpcImpl.java
│   │   │       │   └── persistence/          ← JPA/Panache adapter
│   │   │       │       └── UserRepositoryImpl.java
│   │   │       ├── infrastructure/
│   │   │           ├── security/
│   │   │           │   ├── JwtAuthGrpcInterceptor.java
│   │   │           │   └── TokenProvider.java
│   │   │           └── grpc/
│   │   │               └── GrpcServerConfig.java
│   │   │       └── MyQuarkusApplication.java    ← @QuarkusMain (tùy chọn)
│   │   ├── resources/
│   │   │   ├── proto/                        ← COPY proto ở đây để Dev UI thấy
│   │   │   │   └── my-service.proto
│   │   │   ├── application.yml
│   │   │   ├── application-dev.yml
│   │   │   ├── application-prod.yml
│   │   │   ├── keys/                         ← privateKey.pem, publicKey.pem (git ignore)
│   │   │   ├── db/
│   │   │   │   └── migration/
│   │   │   │       ├── V1.0.0__create_user_table.sql
│   │   │   │       └── V1.0.1__add_index.sql
│   │   │   └── META-INF/resources/           ← nếu có OpenAPI UI, gRPC UI
│   │   └── docker/
│   │       └── Dockerfile
│   │       └── Dockerfile.native
│   └── test/
│       ├── java/
│       │   └── com/company/myapp/
│       │       ├── grpc/
│       │       │         └── MyServiceGrpcTest.java        ← @QuarkusTest + GrpcTestResource
│       │       └── repository/
│       │           └── UserRepositoryTest.java
│       └── resources/
│           └── application-test.yml
├── proto/                                    ← Repo proto riêng (nếu tách module)
└── README.md
```


