# Git仓库使用说明

## 一、提前准备

Windows客户端建议使用git-scm客户端，官方客户端下载地址：https://git-scm.com/downloads。

以下所有`git`操作命令都基于`git`官方客户端（ `Mac`和`Linux`系统下即为虚拟终端的`git`命令；`Windows`系统下推荐使用`Git Bash`命令行下的`git`命令），不保证在其他`git`客户端（尤其是其他`git`的GUI客户端，例如`SourceTree`、`GitHub Desktop`、`TortoiseGit`等等）中有效。

如果你是第一次使`git`，应该需要配置`user.email`和`user.name`，否则你将无法做commit操作。

```bash
$ git config --global user.email "<your email>"
$ git config --global user.name "<your name>"
```

## 二、克隆项目

```bash
$ git clone https://oauth2:xxxxxxx@codechina.csdn.net/xxxx/xxxx.git
```

上面地址只是示例，请使用考前须知中给出的项目地址。地址中包含了该项目的 access token ，拥有它你就拥有该仓库的 pull/push 权限。

你不用额外配置`access token`，我们已为你准备好包含`access token`的项目地址，你只需要`git clone`我们给的项目地址 即可，后续的对该项目的`git`操作你都不用关注`access token 。`

`access token`只为该项目准备，不会对你本地其他任何`git`项目产生影响。

项目中`需求.md`文档描述了本考题所需要完成的任务及相关要求，请认证阅读并按要求完成。

## 三、注意事项

请将代码`push`到线上的`master`分支。

考试结束后，我们将撤销该项目的`access token`，你将失去对该代码仓库的`pull/push`权限。

我们将以你`master`分支上的最后一次提交作为你这次考试的最终成果。

考试期间请不要泄漏你的项目地址给别人，一经发现，本次考试作废。