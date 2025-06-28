<template>
  <div class="app">
    <Hello />
  </div>
</template>

<script setup lang="ts">
import Hello from "./components/Hello.vue";
</script>

<style lang="scss">
@import "./styles/global";
</style>

<style scoped lang="scss">
.app {
  background-color: var(--base-application-background-color);
}
</style>
