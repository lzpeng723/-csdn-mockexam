<template>
  <el-menu class="el-menu-demo" mode="horizontal" :default-active="activeIndex" @select="handleSelect">
    <el-menu-item index="resume" router><router-link to="/resume/new">简历录入</router-link></el-menu-item>
    <el-menu-item index="resumeList" router><router-link to="/resumeList">简历查询</router-link></el-menu-item>
    <el-menu-item index="xss" router><router-link to="/xss">XSS测试</router-link></el-menu-item>
    <el-menu-item index="about" router><router-link to="/about">关于</router-link></el-menu-item>
  </el-menu>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { onBeforeRouteLeave, onBeforeRouteUpdate } from 'vue-router'

const activeIndex = ref('resumeList')

// 与 beforeRouteUpdate 相同，无法访问 `this`
onBeforeRouteUpdate(async (to, from) => {
  //仅当 id 更改时才获取用户，例如仅 query 或 hash 值已更改
  activeIndex.value = to.name as string
})
</script>
