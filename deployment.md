## 所需环境

JDK 1.8+

Tomcat 9

Mysql 5.7+

Idea

## 1 下载源码

```bash
git clone https://oauth2:Gqzn4ZiwtZtMdRk7wbKP@gitcode.net/exam-rooms/c4-primary-java/21123111211545471640920875.git csdn-exam-c4-javabase
```

## 2 docker-compose 方式运行

```bash
cd csdn-exam-c4-javabase
docker-compose up -d
```

然后浏览器打开 http://{ip}:8080/csdn-exam-c4-javabase 访问项目

## 3 编译方式运行

- 在 docker 容器内启动数据库

  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- 因在 [`src/main/resources/db.setting`](./src/main/resources/db.setting) 中配置了数据库连接 ip 为 `db`, 需要手动添加 `host` 配置 `db:数据库机器ip`
- 开发工具启动项目
- 浏览器打开 http://{ip}:{port}/csdn-exam-c4-javabase 访问项目

## 4 打 war 包运行

- 在 docker 容器内启动数据库

  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- 因在 [`src/main/resources/db.setting`](./src/main/resources/db.setting) 中配置了数据库连接 ip 为 `db`, 需要手动添加 `host` 配置 `db:数据库机器ip`
- ```bash
    cd csdn-exam-c4-javabase
    gradlew build war
  ```
- 将 `./build/libs/csdn-exam-c4-javabase.war` 部署到 `tomcat9` 里, 重启`tomcat9`
- 浏览器打开 http://{ip}:{port}/csdn-exam-c4-javabase 访问项目

## 5 手工构建 docker 镜像运行

- 在 docker 容器内启动数据库

  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- ```bash
    cd csdn-exam-c4-javabase
    docker build -t csdn-exam-c4-javabase:latest .
    docker run --name csdn-exam-c4-javabase-web -d --add-host db:{数据库机器ip} -p 8080:8080 csdn-exam-c4-javabase:latest
  ```
- 浏览器打开 http://{ip}:8080/csdn-exam-c4-javabase 访问项目