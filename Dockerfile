FROM jdk21-alpine AS builder
COPY --chown=gradle:gradle . ./
RUN gradle :core:bootJar :tasks-backend:bootJar :notification:bootJar -g /home/gradle/cash --no-daemon

FROM openjdk:21-alpine
RUN mkdir -p /app && apk update && apk add yq postgresql-client gettext curl
WORKDIR /app
COPY --from=builder /home/gradle/core/build/libs/core.jar /app/core.jar
COPY --from=builder /home/gradle/tasks-backend/build/libs/tasks-backend.jar /app/tasks-backend.jar
COPY --from=builder /home/gradle/notification/build/libs/notification.jar /app/notification.jar
ENTRYPOINT sh