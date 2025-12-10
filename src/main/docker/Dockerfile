###
### Stage 1: Build native executable
###
#FROM quay.io/quarkus/ubi-quarkus-graalvm-builder-image:22.3-java17 AS build
#
## Copy toàn bộ project vào container
#COPY . /usr/src/app
#WORKDIR /usr/src/app
#
## Build native
## Thêm -Dquarkus.native.container-build=true bắt buộc khi build trong container
#RUN ./mvnw clean package -Pnative -Dquarkus.native.container-build=true
#
###
### Stage 2: Runtime image (ubi-minimal)
###
#FROM registry.access.redhat.com/ubi8/ubi-minimal:8.8
#
#WORKDIR /work/
#RUN chown 1001 /work \
#    && chmod "g+rwX" /work
#
## Copy file native từ stage build
#COPY --from=build /usr/src/app/target/*-runner /work/application
#
#EXPOSE 8080
#
#USER 1001
#
#ENTRYPOINT ["./application"]

# syntax=docker/dockerfile:1.4
#FROM quay.io/quarkus/ubi-quarkus-graalvm-builder-image:22.3-java17 AS build
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:24.1-arm64 AS build

WORKDIR /workspace
COPY . .

COPY ./settings.xml.xml /workspace/settings-template.xml

# Mount secret cho Maven password
RUN --mount=type=secret,id=maven-pass \
    --mount=type=secret,id=maven-user \
    export MAVEN_PASSWORD=$(cat /run/secrets/maven-pass) && \
    export MAVEN_USERNAME=$(cat /run/secrets/maven-user) && \
    sed \
      -e "s|\${MAVEN_USERNAME}|$MAVEN_USERNAME|g" \
      -e "s|\${MAVEN_PASSWORD}|$MAVEN_PASSWORD|g" \
      settings-template.xml > /workspace/settings.xml && \
    mvn -s /workspace/settings.xml -B clean package -Pnative -Dquarkus.native.container-build=true

FROM registry.access.redhat.com/ubi8/ubi-minimal AS runner
COPY --from=build /workspace/target/*-runner /app/application
ENTRYPOINT ["/app/application"]

