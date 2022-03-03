<template>
  <h1>ENV</h1>
  <pre style="text-align: left;">
    {{ env }}
  </pre>
</template>
<script lang="ts" setup>
import {ref} from "vue"

const env = ref(JSON.stringify(import.meta.env, null, '\t'));
</script>
