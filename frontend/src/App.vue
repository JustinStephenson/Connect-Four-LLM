<template>
  <main class="app">
    <Game />
  </main>
</template>

<script setup lang="ts">
import Game from "./components/Game.vue";
</script>

<style lang="scss">
@use "./styles/global";
</style>

<style scoped lang="scss">
@use "./styles/mixins" as mixin;

.app {
  height: 100vh;
  width: 100vw;
  background-color: var(--base-application-background-color);

  @include mixin.flexCenter;
}
</style>
