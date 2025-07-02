<template>
  <section class="grid">
    <template v-for="slots in slotMatrix">
      <template v-for="slot in slots">
        <Slot />
      </template>
    </template>
  </section>
</template>

<script setup lang="ts">
import Slot from "./Slot.vue";
import { createMatrix } from "../util";

const slotMatrix: number[][] = createMatrix(7, 7);
console.log(slotMatrix);
</script>

<style scoped lang="scss">
.grid {
  width: 50%;
  height: 50%;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(7, 1fr);

  border: 0.5rem solid black;
}
</style>
