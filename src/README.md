## 当前目录一般用来放代码文件。你可根据你的情况自行处理。

##### 测试数据库

```bash
docker run --name c4exam-mysql -e MYSQL_DATABASE=c4exam -e MYSQL_USER=c4exam -e MYSQL_PASSWORD=c4exam -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d -p 3306:3306 mysql
docker build -t c4exam:latest .
docker run --name c4exam-web -d --add-host db:192.168.85.135 -p 8080:8080 c4exam:latest
```
