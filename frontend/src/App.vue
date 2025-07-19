<template>
  <main class="app">
    <Header />
    <Game />
  </main>
</template>

<script setup lang="ts">
import Game from "./components/Game.vue";
import { useMobileResponsive } from "./util";
import Header from "./components/header/Header.vue";

useMobileResponsive();
</script>

<style lang="scss">
@use "./styles/global";
</style>

<style scoped lang="scss">
.app {
  height: calc(var(--vh) * 100);
  width: 100%;
  display: grid;
  grid-template-rows:
    calc(var(--vh) * 10) [header]
    1fr [body];
}
</style>
