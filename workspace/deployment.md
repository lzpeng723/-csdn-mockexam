## 下载源码

```bash

# 通过 github
git clone -b c4-javaproject-exam-answer git@github.com:lzpeng723/csdn-exam.git c4-javaproject-exam-answer
OR
git clone -b c4-javaproject-exam-answer https://github.com/lzpeng723/csdn-exam.git c4-javaproject-exam-answer


# 通过 gitee
git clone -b c4-javaproject-exam-answer git@gitee.com:lzpeng723/csdn-exam.git c4-javaproject-exam-answer
OR
git clone -b c4-javaproject-exam-answer https://gitee.com/lzpeng723/csdn-exam.git c4-javaproject-exam-answer
```

## docker-compose 方式启动

```bash
cd c4-javaproject-exam-answer/workspace
docker-compose up -d --build
```

**该命令成功运行后，您将获得运行的项目。


可通过`http://localhost:9528`访问前端页面。请确保本机的`9528`端口没有被占用。

该前端工程中接口请求地址为`http://localhost:8080`，后端服务请以此地址来提供服务。

因数据库和后端启动较慢，可稍等片刻打开项目
