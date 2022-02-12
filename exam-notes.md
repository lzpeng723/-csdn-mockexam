### 考前须知

C4、C5考核均采用编程马拉松模式进行考核，试题来源于真实工作场景任务需求，要求考生在规定时间内按照需求文档完成程序设计。考试过程中，有监考老师实时监考。
远程考试的考生需保障考试过程中的计算机硬件、网络、电力等环境安全稳定，如因所述因素导致考试过程中断且无法继续考试的，将按考生最后提交结果对项目进行评审。

OBS推流地址和密钥如下(仅供考试使用)：

OBS推流地址  rtmp://xxxxxxxxxxxxxxxxxxxx

OBS推流秘钥  xxxxxxxxxxxxxxxxxxxxxxxxx?auth_key=xxxxxxxxxxxxxxx

Git仓库地址，含有考试需求文档、原型图、必要的素材等，如下(仅供考试使用)：

Git仓库地址  https://oauth2:xxxxxxxxxxxx@gitcode.net/xxxxxxxxxx/xxxxxxx/xxxxxxxxxx.git

### OBS软件![img](https://ks.csdn.net/img/obsstudio.7f8a646d.svg)

为保证认证的公平性和真实性，要求使用OBS软件推流，考试过程中的录屏、录像和认证资料将进入CSDN的人才库。考生需在正式开考前下载并安装OBS软件，熟悉OBS使用方法。考试开始前10分钟，可进入考试详情页面获取推流地址和密钥，请您务必第一时间开启OBS软件，按照教程进行相关设置。

[OBS推流使用指南](./obs.md)

[![img](https://ks.csdn.net/img/download.8b634f99.svg)下载OBS](https://obsproject.com/download)

### Git客户端![img](https://ks.csdn.net/img/git.ab930f5c.svg)

要求使用Git版本控制系统进行项目管理。考试正式开始后，在考试详情页面获取项目仓库地址，仓库中含有考试需求文档、原型图、必要素材等。请确保所编写代码均已提交至远程仓库，成绩审核以考试结束前最后一次提交版本为准。建议考试结束前20分钟确保已经上传一个版本，避免考试结果为空。

[Git仓库使用说明](./git-notes.md)

[![img](https://ks.csdn.net/img/download.8b634f99.svg)下载Git](https://git-scm.com/download)

### Docker容器![img](https://ks.csdn.net/img/docker.cf73b904.svg)

项目评审过程中将使用Docker容器作为运行环境，开发者需在README.md文档中提供详尽的部署与配置说明。项目评审时，如果根据README.md文档无法正常运行，则会直接使用服务编排文件启动(即运行命令docker-compose up)，如果依然无法正常运行项目，将终止审核。

[Docker部署要求](./docker-deploy.md)

[![img](https://ks.csdn.net/img/download.8b634f99.svg)下载Docker](https://www.docker.com/products/docker-desktop)

> **重要提醒：以下情况将直接判定考试不通过**
>
> *·* OBS: 全程没有露过全脸，看不见计算机屏幕上编码过程*·* Git: 项目源代码没有使用Git工具推送到服务器*·* Docker: 无法通过docker-compose.yml配置文件运行Docker服务

**电脑配置**  为保障考生顺利进行考试，请确保考试使用的计算机硬件配置达到CPU：7代i5以上，内存：8G以上。

**意外处理**  如遇到机器死机、断电等情况，重启计算机后开启OBS重新推流即可继续考试。系统无法恢复运行的，请立即更换计算机，重新登录考试认证中心官网，参照说明重新设置OBS，克隆Git仓库继续考试。建议考试期间，定期推送代码到服务器，防止意外因素导致代码丢失。

