<template>
  <section class="main">
    <Board
      :slot-matrix="slotMatrix"
      :current-player="currentPlayer"
      @has-clicked="changePlayer()"
    />
  </section>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Board from "./board/Board.vue";
import { createMatrix } from "../util";
import type { TokenType } from "../types/tokenType.ts";

const slotMatrix: TokenType[][] = createMatrix(7, 7);
const players: Record<string, TokenType> = {
  PlayerOne: 1 as TokenType,
  PlayerTwo: 2 as TokenType,
};
const currentPlayer = ref<TokenType>(players.PlayerOne);

const changePlayer = () => {
  console.log(currentPlayer.value);
  currentPlayer.value =
    currentPlayer.value === players.PlayerOne
      ? players.PlayerTwo
      : players.PlayerOne;
};
</script>

<style scoped lang="scss">
@use "./../styles/mixins" as mixin;

.main {
  width: 100%;
  height: 100%;

  @include mixin.flexCenter;
}
</style>
