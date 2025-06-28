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
@import "./styles/mixins";

.app {
  height: 100vh;
  width: 100vw;
  background-color: var(--base-application-background-color);

  @include flexCenter;
}
</style>
