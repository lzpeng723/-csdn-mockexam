<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="csdn.c4.dao.DbUtil" %>
<%
    String path = request.getContextPath();
    int port = request.getServerPort();
    String baseurl = request.getScheme() + "://" + request.getServerName() + ":" + port + path;
    // 初始化数据
    //noinspection UnhandledExceptionInJSP
    Class.forName(DbUtil.class.getName());
%>
<script>
    const baseUrl = '<%=baseurl%>';
</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>C4 Java 基础模拟考试</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <link rel="icon">
    <!-- import CSS -->
    <link rel="stylesheet" href="https://unpkg.com/element-plus/dist/index.css">
    <!-- import JavaScript -->
    <script src="https://unpkg.com/vue@next"></script>
    <script src="https://unpkg.com/element-plus"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="./css/index.css" as="style"/>
</head>
<body>
<div id="app">
    <el-container>
        <el-header class="csdn-c4-header">
            <div class="csdn-c4-header-left">
                <h1>C4 Java 基础模拟考试</h1>
                <el-input v-model="currentFileName"
                          placeholder="请输入要搜索的文件名"
                          @keyup.enter="searchFileData()">
                    <template #append>
                        <el-button @click="searchFileData()">搜索</el-button>
                    </template>
                </el-input>
            </div>
            <div class="csdn-c4-header-right">
                <el-space wrap>
                    <el-link :href="viewDbUrl" type="primary" target="_blank">点击查看数据库,密码 csdn-mockexam-c4-javabase</el-link>
                    <el-button
                            v-loading.fullscreen.lock="fullscreenLoading"
                            element-loading-text="初始化数据"
                            :element-loading-spinner="svg"
                            element-loading-svg-view-box="-10, -10, 50, 50"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            @click="refreshData">
                        初始化数据
                    </el-button>
                </el-space>
            </div>
        </el-header>
        <el-main class="csdn-c4-main">
            <div><h3>{{ '文件所在文件夹:' + fileData.parentFile }}</h3></div>
            <el-space wrap>
                <el-card v-for="file in fileData.files"
                         :key="file.file_name"
                         @click="this.searchFileData(file.file_name)"
                         class="box-card">
                    <h3>{{ '文件名:' + file.file_name }}</h3>
                </el-card>
            </el-space>
            <el-card class="box-card" style="width: 100%">
                <template #header>
                    <div class="card-header">
                        <h2>当前文件名:</h2>
                        <span>{{ currentFileName }}</span>
                    </div>
                </template>
                <pre v-html="currentFileContent"></pre>
            </el-card>
        </el-main>
        <el-footer>
        </el-footer>
    </el-container>
</div>
<script charset="UTF-8" src="./js/index.js"></script>
</body>
</html>