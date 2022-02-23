<template>
  <BaseHeader/>
  <img alt="Vue logo" class="element-plus-logo" :src="logo"/>
  <HelloWorld msg="Hello Vue 3.0 + Vue Router + Vuex + Element Plus + Vite"/>
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
