
# 第一阶段：环境构建;
FROM maven:3.5.0-jdk-17-alpine AS builder
WORKDIR /app
ADD ./ /app
RUN mvn clean package -Dmaven.test.skip=true
# 第二阶段，最小运行时环境，只需要jre
FROM openjdk:17-jre-alpine
# 修改时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
LABEL maintainer="chengdong2518@163.com"
# 从上一个阶段复制内容
COPY --from=builder /app/message-center-boot/target/message-center-boot.jar /message-center-boot.jar
ENV JAVA_OPTS=""
ENV PARAMS=""
# 运行jar包
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -jar /message-center-boot.jar $PARAMS" ]