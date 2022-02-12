# 基础镜像使用 docker pull gradle:7.3.3-jdk17-alpine
FROM gradle AS build
# 工作目录
WORKDIR /home/gradle/project
# 复制文件
COPY . /home/gradle/project
# 安装依赖 打包 https://github.com/gradle/gradle/issues/17159
RUN gradle jar --no-watch-fs --scan --debug --stacktrace
# RUN gradle jar
# 使用openjdk:18-ea-11-alpine3.15镜像
FROM  openjdk:18-ea-11-alpine3.15
# 复制文件
COPY --from=build /home/gradle/project/build/libs /
# 工作目录
WORKDIR /libs
ENTRYPOINT  ["java","-jar", "csdn-exam-c4-javabase.jar"]