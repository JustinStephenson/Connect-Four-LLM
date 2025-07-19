<template>
  <section class="board">
    <div class="hover">
      <template v-for="(_, columnIndex) in props.slotMatrix[0].length">
        <div
          class="hover__pointer"
          :class="{ active: hoveredColumn === columnIndex }"
        >
          <div v-if="hoveredColumn === columnIndex">
            <Token />
          </div>
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
import Token from "./Token.vue";

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
  width: 40%;
  height: 60%;
  display: grid;
  grid-template-rows: 2fr 10fr;
}

.grid {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(7, 1fr);
  border: 0.5rem solid black;
}

.hover {
  display: grid;
  grid-template-columns: repeat(7, 1fr);

  &__pointer {
    display: flex;
    align-items: center;
    justify-content: center;
    opacity: 0;
    transition: opacity 0.2s;

    &.active {
      opacity: 1;
    }
  }
}
</style>
