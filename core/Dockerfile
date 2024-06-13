FROM gitlab.metasharks.ru:5005/docker/origin/gradle:7.6.0-jdk21-alpine AS builder
COPY --chown=gradle:gradle ../../Новая%20папка/tasks-backend ./
RUN gradle :core:bootJar -g /home/gradle/cash --no-daemon

FROM gitlab.metasharks.ru:5005/docker/origin/openjdk:19-alpine
RUN mkdir -p /app && apk update && apk add yq postgresql-client gettext curl
WORKDIR /app
COPY --from=builder /home/gradle/core/build/libs/core.jar /app/core.jar
ENTRYPOINT sh