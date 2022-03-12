# 基础镜像使用maven:3.6.1-jdk-8-alpine
FROM maven:3.6.1-jdk-8-alpine AS build
# 工作目录
WORKDIR /home/maven/project
# 复制文件
COPY ./project /home/maven/project
# 安装依赖 打包
RUN mvn package


# 基础镜像使用openjdk:8-jdk-alpine
FROM openjdk:8-jdk-alpine
# 将jar包添加到容器中并更名为app.jar
COPY --from=build /home/maven/project/target/c4p-check.jar /home/java/project/c4p-check.jar
# 暴漏端口
EXPOSE 8080
# 运行jar包
# 覆盖ENTRYPOINT: docker run --name workspace_back_end_001 -it --entrypoint /bin/sh workspace_back_end
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/java/project/c4p-check.jar"]