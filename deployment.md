## 所需环境

JDK 11

Mysql 5.7+

Idea

## 1 下载源码

```bash
git clone https://oauth2:Gqzn4ZiwtZtMdRk7wbKP@gitcode.net/exam-rooms/c4-primary-java/21123111211545471640920875.git csdn-exam-c4-javabase
```

## 1 编译方式运行

- 在 docker 容器内启动数据库

  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- 因在 [`src/main/resources/db.setting`](./src/main/resources/db.setting) 中配置了数据库连接 ip 为 `db`, 需要手动添加 `host` 配置 `db:数据库机器ip`
- 开发工具启动项目 运行 `csdn.c4.Main`, 即可生成 `./workspace/企业员工月度工资成本支付表.xlsx`

Docker 镜像没有弄好
## ~~2 手工构建 docker 镜像运行~~

- 在 docker 容器内启动数据库

  `docker run --name csdn-exam-c4-javabase-mysql -e MYSQL_DATABASE="csdn-exam-c4-javabase" -e MYSQL_USER="csdn-exam-c4-javabase" -e MYSQL_PASSWORD="csdn-exam-c4-javabase" -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql`
- ```bash
    cd csdn-exam-c4-javabase
    docker build -t csdn-exam-c4-javabase:latest .
    docker run --name csdn-exam-c4-javabase-01 --add-host db:{数据库机器ip} csdn-exam-c4-javabase:latest
  ```
- 查看命令行结果