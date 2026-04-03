# STAGE 1: Build stage (Debian-based để khớp glibc)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Tối ưu JRE với đầy đủ module cho Spring Boot JPA
RUN jlink \
    --add-modules java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,jdk.unsupported,java.compiler,java.xml,jdk.crypto.ec \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /javaruntime

# STAGE 2: Runtime (Giữ nguyên Distroless CC như đã sửa)
FROM gcr.io/distroless/cc-debian11:latest
WORKDIR /app

# Copy Custom JRE và App
COPY --from=build /javaruntime /opt/java
COPY --from=build /app/target/*.jar app.jar

# Cấu hình môi trường vận hành thực tế [cite: 100, 250]
ENV PATH="/opt/java/bin:$PATH"
USER nonroot

# Sử dụng ENTRYPOINT dạng exec để đảm bảo nhận tín hiệu SIGTERM (PID 1) [cite: 250, 253]
ENTRYPOINT ["/opt/java/bin/java", "-jar", "app.jar"]