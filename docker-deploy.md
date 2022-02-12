# Docker部署要求

## 一、项目部署要求

Q: 项目以何种方式部署？

A: 要求您的项目最终可以以`docker-compose.yml文件编排一个或多个docker容器`的方式来部署

Q: 为什么要使用Docker容器及docker-compose.yml编排文件来部署项目？

A: Docker容器技术以及docker-compose容器编排技术能最大限度的保证您的项目在开发环境和生产环境上的一致表现。

Q: 如果无法以`docker-compose.yml文件编排一个或多个docker容器`的方式来部署运行项目是否就意味着通不过考试？

A: 是的

## 二、Windows10系统安装Docker Desktop

随着Windows10推出了`WSL2`，在Windows10系统上使用Docker也越来越方便。下面介绍一下如何在Windows10(无论专业版还是家庭版)上安装`Docker Desktop`。

在Docker官网下载最新的`Docker Desktop`安装，下载地址：[https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)

Docker Desktop启动的时候，有可能提示"`WSL2 installations is incomplete`"，这是您的系统中没有安装WSL2内核的原因，打开【 [https://aka.ms/wsl2kernel](https://aka.ms/wsl2kernel) 】,在打开的页面中有一个"`适用于x64计算机的WSL2 Linux内核更新包`"链接，点击下载，安装。

`WSL2 Linux`内核更新包安装后，重启`Docker Desktop`即可正常使用。您可在`cmd`或者`PowerShell`命令行中使用`docker`或者`docker-compose`等相关命令了。

**PS:** 如果您在安装`WSL2`的过程中遇到了问题，可能是您的系统版本较低等原因，您可按照【 [https://aka.ms/wsl2kernel](https://aka.ms/wsl2kernel) 】页面的相关提示更新系统。该`Docker Desktop`的安装方法基于`Windows10`的`WSL2`如果您的系统没有或者不能安装`WSL2`，可能不能使用该方法安装`Docker Desktop`。

## 三、其他

docker相关文档：[https://docs.docker.com/get-started/](https://docs.docker.com/get-started/)

docker-compose相关文档：[https://docs.docker.com/compose/gettingstarted/](https://docs.docker.com/compose/gettingstarted/)

相关中文文档请自行搜索查找