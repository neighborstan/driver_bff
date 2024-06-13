FROM gitlab.metasharks.ru:5005/docker/origin/gradle:7.6.0-jdk21-alpine AS builder
COPY --chown=gradle:gradle ../../Новая%20папка/tasks-backend ./
RUN gradle :tasks-backend:bootJar -g /home/gradle/cash --no-daemon

FROM gitlab.metasharks.ru:5005/docker/origin/openjdk:19-alpine
RUN mkdir -p /app && apk update && apk add yq postgresql-client gettext curl
WORKDIR /app
COPY --from=builder /home/gradle/api/build/libs/api.jar /app/api.jar
COPY --from=builder /home/gradle/watcher/build/libs/watcher.jar /app/watcher.jar
COPY --from=builder /home/gradle/notification/build/libs/notification.jar /app/notification.jar
ENTRYPOINT sh