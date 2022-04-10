# 基础镜像使用maven:3.6.1-jdk-8-alpine
FROM maven:3.6.1-jdk-8-alpine AS build
# 工作目录
WORKDIR /home/maven/project
# 复制文件
COPY ./backend_server /home/maven/project
# 安装依赖 打包
RUN mvn package -Dmaven.test.skip=true


# 基础镜像使用java:8
FROM java:8
# 将jar包添加到容器中
COPY --from=build /home/maven/project/target/backend_server.jar /home/java/project/c4-exam.jar
# 暴漏端口
EXPOSE 8080
# 运行jar包
# 覆盖ENTRYPOINT: docker run --name workspace_back_end_001 -it --entrypoint /bin/sh workspace_back_end
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-Duser.timezone=Asia/Shanghai","-Djava.security.egd=file:/dev/./urandom","-jar","/home/java/project/c4-exam.jar"]
