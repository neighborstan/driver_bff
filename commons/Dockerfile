# Этап сборки
FROM gradle:8.7.0-jdk21-alpine AS builder
WORKDIR /app
COPY --chown=gradle:gradle . ./
RUN gradle :commons:bootJar -g /home/gradle/cache --no-daemon

# Финальный образ
FROM openjdk:21-alpine
RUN mkdir -p /app && apk update && apk add --no-cache yq postgresql-client gettext curl
WORKDIR /app
COPY --from=builder /app/build/libs/commons.jar /app/commons.jar
ENTRYPOINT ["sh"]
