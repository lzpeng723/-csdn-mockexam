# 基础镜像使用gradle:7-alpine
FROM gradle:7-alpine AS build
# 工作目录
WORKDIR /home/gradle/project
# 复制文件
COPY . /home/gradle/project
# 安装依赖 打包
RUN gradle build war
# 使用tomcat:9-jre8-alpine镜像
FROM tomcat:9-jre8-alpine
# 复制文件
COPY --from=build /home/gradle/project/build/libs/csdn-mockexam-c4-javabase.war /usr/local/tomcat/webapps
# 暴漏端口
EXPOSE 8080
# 运行tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]