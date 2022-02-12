# OBS推流使用指南

<video src="https://csdnlive.oss-cn-shenzhen.aliyuncs.com/example/obs-min.mp4"></video>


## 指南介绍

- 本指南主要介绍在 Windows 和 Mac 操作系统下使用 OBS 采集视频并实现上行 RTMP 推流。
- OBS 是一款第三方开源免费的直播软件，目前支持 OS X、Windows、Linux 操作系统。
- OBS 官网可以下载到适合您操作系统的版本，下载完成后，请按照引导进行安装。

[设置OBS时无法捕获桌面，可能由于使用的计算机系统有双显卡导致，请先进行系统显示设置以正常启用OBS，查看教程>>](https://ks.csdn.net/help/doubleCard)

## 步骤一 : 获取推流地址

在已报名C4或C5相关考试后，登录【CSDN能力认证中心官网】，点击右上角【考试】，**当考试开始后**会自动生成【推流地址】和【推流密钥】，以及本次考试的【Git仓库地址（内含考试相关信息）】

## 步骤二 : 设置推流参数

1. 打开OBS应用，点击主界面右下方【控件】-【设置】，进入设置界面

![img](https://ks.csdn.net/img/obs1.4e6df6fc.png)

2. 在设置界面，点击左侧功能栏的【推流】选项，进入推流服务设置页面，填好对应内容点击【应用】保存设置信息，然后点击【确定】

![img](https://ks.csdn.net/img/obs2.0cdc18c5.jpg)

## 步骤三 : 设置【显示器采集】和【视频采集设备】

1. 在主界面下方【来源】区域单击【+】号，选择【显示器采集】，在弹出来的【新建】窗口点击【确定】；如果显示的画面不完全，那么可以在【来源】区域已添加的【显示器采集】,右键选择【调整输出大小(到源大小)】，这时弹出提示【基准与输出分辨率将调整为当前源大小。您是否想要继续?】选择【是】

![img](https://ks.csdn.net/img/obs3.b7edf4b6.png)

不完整画面与完整画面对比图如下

![img](https://ks.csdn.net/img/obs7.5ffa9a9a.png)

2. 在主界面下方【来源】区域单击【+】号，选择【视频采集设备】，在弹出来的【新建】窗口点击【确定】；
   接下来在弹出【属性'视频采集设备'】窗口中的【设备】一栏选择默认的【FaceTime高清摄像头(内建)】选项，这时会出现摄像头画面，点击【确定】；
   调整摄像头画面到适当位置和大小，建议放在屏幕右上角尽量不遮挡代码，在摄像头画面上右键菜单选择【排序-移至顶层】保证摄像头画面在最上层

![img](https://ks.csdn.net/img/obs4.d071762d.jpg)

台式机：必须拥有摄像头才能支持【视频采集设备】
笔记本：基本都拥有摄像头无需额外安装摄像头
考试必须录屏 + 录像

## 步骤四 : 开始推流 (推流成功即表示已经开始将本机的录屏和录像上传到服务器)

在主界面右下方【控件】面板，**单击【开始推流】，底部出现绿灯，表示推流成功**，已经开始将本机的录屏和录像上传到服务器。在完成所有考试任务后点击【停止推流】

![img](https://ks.csdn.net/img/obs5.cd950c58.png)

如果出现频繁掉线、重连这种现象可能

1. 您填写的推流地址或推流密钥有可能输错了（认真检查一遍）
2. 您的网络可能不太稳定，丢包严重

温馨提示

设置好OBS后，点击窗口左上角【最小化】收起OBS窗口，可提高计算机响应速率。

![img](https://ks.csdn.net/img/obs8.1fbeeecd.png)