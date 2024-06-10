FROM gitlab.metasharks.ru:5005/docker/origin/gradle:7.6.0-jdk19-alpine AS builder
COPY --chown=gradle:gradle . ./
RUN gradle :core:bootJar :task-backend:bootJar -g /home/gradle/cash --no-daemon

FROM gitlab.metasharks.ru:5005/docker/origin/openjdk:19-alpine
RUN mkdir -p /app && apk update && apk add yq postgresql-client gettext curl
WORKDIR /app
COPY --from=builder /home/gradle/core/build/libs/core.jar /app/core.jar
COPY --from=builder /home/gradle/task-backend/build/libs/task-backend.jar /app/task-backend.jar
ENTRYPOINT sh