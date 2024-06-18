# Этап сборки
FROM gradle:8.7.0-jdk21-alpine AS builder
WORKDIR /app
COPY --chown=gradle:gradle . ./
RUN gradle \
    :tasks:api:bootJar \
    :tasks:watcher:bootJar \
    :users:api:bootJar \
    :users:watcher:bootJar \
#    notification:bootJar \
#    incidents:api:bootJar \
#    incidents:watcher:bootJar \
    -g /home/gradle/cache \
    --no-daemon

# Финальный образ
FROM gradle:8.7.0-jdk21-alpine
RUN mkdir -p /app && apk update && apk add --no-cache yq postgresql-client gettext curl
WORKDIR /app
COPY --from=builder /app/tasks/api/build/libs/api.jar /app/tasks/api.jar
COPY --from=builder /app/tasks/watcher/build/libs/watcher.jar /app/tasks/watcher.jar
COPY --from=builder /app/users/api/build/libs/api.jar /app/users/api.jar
COPY --from=builder /app/users/watcher/build/libs/watcher.jar /app/users/watcher.jar
#COPY --from=builder /app/notification/build/libs/notification.jar /app/notification.jar
#COPY --from=builder /app/incidents/api/build/libs/api.jar /app/incidents/api.jar
#COPY --from=builder /app/incidents/watcher/build/libs/watcher.jar /app/incidents/watcher.jar
ENTRYPOINT sh
