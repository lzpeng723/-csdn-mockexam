# 基础镜像使用gradle:7-alpine
FROM gradle:7-alpine AS build
# 工作目录
WORKDIR /home/gradle/project
# 复制文件
COPY . /home/gradle/project
# 安装依赖 打包
RUN gradle build jar
# 使用openjdk:18-ea-11-alpine3.15镜像
FROM  openjdk:18-ea-11-alpine3.15
# 复制文件
COPY --from=build /home/gradle/project/build/libs/csdn-exam-c4-javabase.jar /
COPY --from=build /home/gradle/project/lib /
ENTRYPOINT  ["java","-cp","/lib", "-jar", "csdn-exam-c4-javabase.jar"]