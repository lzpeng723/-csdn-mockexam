## 所需环境

JDK 11

Mysql 5.7+

Idea

## 1 下载源码

```bash
# 通过 github
git clone -b c4-javabase-exam-answer git@github.com:lzpeng723/csdn-exam.git csdn-exam-c4-javabase-answer
OR
git clone -b c4-javabase-exam-answer https://github.com/lzpeng723/csdn-exam.git csdn-exam-c4-javabase-answer

# 通过 gitee
git clone -b c4-javabase-exam-answer git@gitee.com:lzpeng723/csdn-exam.git csdn-exam-c4-javabase-answer
OR
git clone -b c4-javabase-exam-answer https://gitee.com/lzpeng723/csdn-exam.git csdn-exam-c4-javabase-answer
```

## 2 编译方式运行

- 在 docker 容器内启动数据库

  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- 因在 [`src/main/resources/db.setting`](./src/main/resources/db.setting) 中配置了数据库连接 ip 为 `db`, 需要手动添加 `host` 配置 `db:数据库机器ip`
- 开发工具启动项目 运行 `csdn.c4.Main`, 即可生成 `./workspace/企业员工月度工资成本支付表.xlsx`
- 浏览器访问 ip:8080 下载最终文件

## 3 打成 jar 包方式运行

- 在 docker 容器内启动数据库
  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- 因在 [`src/main/resources/db.setting`](./src/main/resources/db.setting) 中配置了数据库连接 ip 为 `db`, 需要手动添加 `host` 配置 `db:数据库机器ip`
- ```bash
  cd csdn-exam-c4-javabase-answer
  gradlew jar
  cd ./build/libs
  java -jar csdn-exam-c4-javabase.jar
  ```
- 即可生成 `./build/libs/workspace/企业员工月度工资成本支付表.xlsx`
- 浏览器访问 ip:8080 下载最终文件

## 4 手工构建 docker 镜像运行

- 在 docker 容器内启动数据库

  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- ```bash
    cd csdn-exam-c4-javabase-answer
    docker build -t csdn-exam-c4-javabase:latest .
    docker run --name csdn-exam-c4-javabase-01 --add-host db:{数据库机器ip} -p 8080:8080 csdn-exam-c4-javabase:latest
  ```
- 浏览器访问 ip:8080 下载最终文件

## 5 docker-compose 方式运行
- ```bash
    cd csdn-exam-c4-javabase-answer
    docker-compose up -d
  ```
- 浏览器访问 ip:8080 下载最终文件
