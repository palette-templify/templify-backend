# 1. 빌드 스테이지 (Gradle 빌드 수행)
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# 프로젝트 전체 복사 (settings.gradle, build.gradle 포함)
COPY . .

# 환경변수로 받을 모듈 이름을 지정 (예: write, transform, auth)
ARG TARGET_MODULE

# 해당 모듈의 JAR 파일 빌드
RUN gradle ${TARGET_MODULE}:bootJar --no-daemon

# 2. 런타임 스테이지 (JRE로 실행)
FROM eclipse-temurin:17-jre
WORKDIR /app

ARG TARGET_MODULE

# 빌드된 JAR 파일을 복사하여 실행
COPY --from=builder /app/${TARGET_MODULE}/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]