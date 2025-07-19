<template>
  <section class="main">
    <Board :slot-matrix="slotMatrix" />
  </section>
</template>

<script setup lang="ts">
import Board from "./board/Board.vue";
import { createMatrix } from "../util";
import type { Types } from "../types/types.ts";

const slotMatrix: Types[][] = createMatrix(7, 7);
</script>

<style scoped lang="scss">
@use "./../styles/mixins" as mixin;

.main {
  width: 100%;
  height: 100%;

  @include mixin.flexCenter;
}
</style>
