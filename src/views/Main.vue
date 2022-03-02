<template>
  <BaseHeader/>
  <!-- 路由匹配到的组件将渲染在这里 -->
  <div style="padding: 0 10%">
    <router-view></router-view>
  </div>
</template>

<script lang="ts" setup>
import BaseHeader from "~/components/layouts/BaseHeader.vue";
import HelloWorld from "~/components/HelloWorld.vue";
import logo from "~/assets/logo.png";
</script>

<style scoped>
.element-plus-logo {
  width: 50%;
}
</style>
