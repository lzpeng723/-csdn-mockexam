## 所需环境

JDK 1.8+

Tomcat 9

Mysql 5.7+

Idea

## 1 下载源码

```bash
git clone git@github.com:lzpeng723/c4exam.git
OR
git clone https://github.com/lzpeng723/c4exam.git
```

## 2 docker-compose 方式运行

```bash
cd c4exam
docker-compose up -d
```

然后浏览器打开 http://{ip}:8080/c4exam 访问项目

## 3 编译方式运行

- 在 docker 容器内启动数据库
  
  `docker run --name c4exam-mysql -e MYSQL_DATABASE=c4exam -e MYSQL_USER=c4exam -e MYSQL_PASSWORD=c4exam -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- 因在 [`src/main/resources/db.setting`](./src/main/resources/db.setting) 中配置了数据库连接 ip 为 `db`, 需要手动添加 `host` 配置 `db:数据库机器ip`
- 开发工具启动项目
- 浏览器打开 http://{ip}:{port}/c4exam 访问项目

## 4 打 war 包运行

- 在 docker 容器内启动数据库
  
  `docker run --name c4exam-mysql -e MYSQL_DATABASE=c4exam -e MYSQL_USER=c4exam -e MYSQL_PASSWORD=c4exam -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- 因在 [`src/main/resources/db.setting`](./src/main/resources/db.setting) 中配置了数据库连接 ip 为 `db`, 需要手动添加 `host` 配置 `db:数据库机器ip`
- ```bash
    cd c4exam
    gradlew build war
  ```
- 将 `./build/libs/c4exam.war` 部署到 `tomcat9` 里, 重启`tomcat9`
- 浏览器打开 http://{ip}:{port}/c4exam 访问项目

## 5 手工构建 docker 镜像运行

- 在 docker 容器内启动数据库
  
  `docker run --name c4exam-mysql -e MYSQL_DATABASE=c4exam -e MYSQL_USER=c4exam -e MYSQL_PASSWORD=c4exam -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- ```bash
    cd c4exam
    docker build -t c4exam:latest .
    docker run --name c4exam-web -d --add-host db:{数据库机器ip} -p 8080:8080 c4exam:latest
  ```
- 浏览器打开 http://{ip}:8080/c4exam 访问项目