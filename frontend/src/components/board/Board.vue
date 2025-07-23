<template>
  <section class="board">
    <div class="hover">
      <template v-for="(_, columnIndex) in props.slotMatrix[0].length">
        <div
          class="hover__pointer"
          :class="{ active: hoveredColumn === columnIndex }"
        >
          <div v-if="hoveredColumn === columnIndex">
            <Token :number="props.currentPlayer" />
          </div>
        </div>
      </template>
    </div>

    <div class="grid">
      <template v-for="rows in props.slotMatrix">
        <template v-for="(number, columnIndex) in rows">
          <Slot
            :number="number"
            @click="onClick(columnIndex)"
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
import type { PlayerPos, TokenType } from "../../types/types.ts";
import { placeToken } from "../../util";

type BoardProps = {
  slotMatrix: TokenType[][];
  currentPlayer: TokenType;
};
const props = defineProps<BoardProps>();

type BoardEmits = {
  (emit: "playerPos", playerPos: PlayerPos): void;
};
const emit = defineEmits<BoardEmits>();

const hoveredColumn = ref<number | null>(null);

const emitPlayerPos = (row: number, columnIndex: number) => {
  emit("playerPos", { row: row, col: columnIndex });
};
const onClick = (columnIndex: number) => {
  placeToken(props.slotMatrix, props.currentPlayer, columnIndex, (row, col) =>
    emitPlayerPos(row, col),
  );
};
const onMouseEnter = (columnIndex: number) => {
  hoveredColumn.value = columnIndex;
};
const onMouseLeave = () => {
  hoveredColumn.value = null;
};
</script>

<style scoped lang="scss">
.board {
  width: 75rem;
  height: 75rem;
  display: grid;
  grid-template-rows: 1.5fr 10fr;
}

.grid {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-template-rows: repeat(6, 1fr);
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
