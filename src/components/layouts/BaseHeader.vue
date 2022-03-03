<template>
  <el-menu class="el-menu-demo" mode="horizontal" :default-active="activeIndex" @select="handleSelect">
    <el-menu-item index="/resume" router><router-link to="/resume">简历录入</router-link></el-menu-item>
    <el-menu-item index="/resumeList" router><router-link to="/resumeList">简历查询</router-link></el-menu-item>
    <el-menu-item index="/xss" router><router-link to="/xss">XSS测试</router-link></el-menu-item>
    <el-menu-item index="/about" router><router-link to="/about">关于</router-link></el-menu-item>
  </el-menu>
</template>
<script lang="ts" setup>
import { ref } from 'vue'

const activeIndex = ref('/resume')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(activeIndex, key, keyPath)
}
</script>
