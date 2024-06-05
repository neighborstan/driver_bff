FROM gitlab.metasharks.ru:5005/docker/origin/gradle:7.6.0-jdk19-alpine AS builder
COPY --chown=gradle:gradle . ./
RUN gradle :api:bootJar :adapter:bootJar :notification:bootJar -g /home/gradle/cash --no-daemon

FROM gitlab.dellin.ru:5005/docker/origin/openjdk:19-alpine
RUN mkdir -p /app && apk update && apk add yq postgresql-client gettext curl
WORKDIR /app
COPY --from=builder /home/gradle/api/build/libs/api.jar /app/api.jar
COPY --from=builder /home/gradle/adapter/build/libs/adapter.jar /app/adapter.jar
COPY --from=builder /home/gradle/notification/build/libs/adapter.jar /app/adapter.jar
ENTRYPOINT sh