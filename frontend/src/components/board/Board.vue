<template>
  <section class="board">
    <div class="arrow">
      <template v-for="(_, columnIndex) in props.slotMatrix[0].length">
        <div
          class="arrow__pointer"
          :class="{ active: hoveredColumn === columnIndex }"
        >
          <span v-if="hoveredColumn === columnIndex">↓</span>
        </div>
      </template>
    </div>

    <div class="grid">
      <template v-for="rows in props.slotMatrix">
        <template v-for="(_, columnIndex) in rows">
          <Slot
            @mouseenter="onMouseEnter(columnIndex)"
            @mouseleave="onMouseLeave()"
          />
        </template>
      </template>
    </div>
  </section>
</template>

<script setup lang="ts">
import Slot from "./Slot.vue";
import { ref } from "vue";

type BoardProps = {
  slotMatrix: number[][];
};
const props = defineProps<BoardProps>();
console.log(props.slotMatrix);

const hoveredColumn = ref<number | null>(null);

const onMouseEnter = (columnIndex: number) => {
  hoveredColumn.value = columnIndex;
};
const onMouseLeave = () => {
  hoveredColumn.value = null;
};
</script>

<style scoped lang="scss">
.board {
  width: 50%;
  height: 50%;
  display: grid;
  grid-template-rows: 1fr 10fr;
}

.grid {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(7, 1fr);

  border: 0.5rem solid black;
}

.arrow {
  display: grid;
  grid-template-columns: repeat(7, 1fr);

  &__pointer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 2rem;
    font-weight: bold;
    color: black;
    opacity: 0;
    transition: opacity 0.2s;

    &.active {
      opacity: 1;
    }
  }
}
</style>
