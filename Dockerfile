# 基础镜像使用 docker pull gradle:7-alpine
FROM gradle:7-alpine AS build
# 工作目录
WORKDIR /home/gradle/project
# 复制文件
COPY . /home/gradle/project
# 安装依赖 打包 https://github.com/gradle/gradle/issues/17159
RUN gradle jar
# 使用openjdk:11.0-jre镜像
FROM openjdk:11.0-jre
# 复制文件
COPY --from=build /home/gradle/project/build/libs /home/java/project
# 暴漏端口
EXPOSE 8080
# 工作目录
WORKDIR /home/java/project
ENTRYPOINT ["java", "-jar", "csdn-exam-c4-javabase.jar"]