# STAGE 1: Build & Create Custom JRE
# Sử dụng image Maven có sẵn để tránh 'apk add maven' mất 126s 
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Tận dụng Cache Mount để lần build thứ 2 chỉ mất vài giây [cite: 142, 143]
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# TẠO CUSTOM JRE (Cốt lõi để đạt kích thước siêu nhỏ) [cite: 53]
RUN jlink \
    --add-modules java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /javaruntime

# STAGE 2: Runtime Stage (Chuẩn bảo mật tuyệt đối)
# Sử dụng Distroless để không có Shell, giảm bề mặt tấn công 
FROM gcr.io/distroless/static-debian11:latest
WORKDIR /app

# Copy Custom JRE và App Artifact
COPY --from=build /javaruntime /opt/java
COPY --from=build /app/target/*.jar app.jar

# Thiết lập biến môi trường cho Java
ENV PATH="$PATH:/opt/java/bin"
USER nonroot

# Healthcheck Native (Có thể dùng Java class hoặc entrypoint thông minh)
# Đối với Distroless, nên dùng cơ chế Healthcheck của Orchestrator (K8s) 
# hoặc script Java đơn giản bên trong app 

ENTRYPOINT ["/opt/java/bin/java", "-jar", "app.jar"]