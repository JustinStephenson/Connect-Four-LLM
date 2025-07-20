<template>
  <section class="main">
    <div class="status">{{ status }}</div>
    <div class="view">
      <div>Player1</div>
      <Board
        :slot-matrix="slotMatrix"
        :current-player="currentPlayer"
        @player-pos="onPlayedToken"
      />
      <Message />
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Board from "./board/Board.vue";
import { checkWin, createMatrix } from "../util";
import type { PlayerPos, TokenType } from "../types/types.ts";
import Message from "./Message.vue";

const slotMatrix: TokenType[][] = createMatrix(7, 7);
const players: Record<string, TokenType> = {
  PlayerOne: 1 as TokenType,
  PlayerTwo: 2 as TokenType,
};
const currentPlayer = ref<TokenType>(players.PlayerOne);
const status = ref<string>("");

const onPlayedToken = (playerPos: PlayerPos) => {
  const hasWon = checkWin(
    slotMatrix,
    currentPlayer.value,
    playerPos.row,
    playerPos.col,
  );
  if (hasWon) {
    status.value = "Player " + currentPlayer.value + " has won!";
  } else {
    changePlayer();
  }
};
const changePlayer = () => {
  if (currentPlayer.value === players.PlayerOne) {
    currentPlayer.value = players.PlayerTwo;
  } else {
    currentPlayer.value = players.PlayerOne;
  }
};
</script>

<style scoped lang="scss">
@use "./../styles/mixins" as mixin;

.main {
  width: 100%;
  height: 100%;

  display: grid;
  place-items: center;
  grid-template-rows: 1fr 10fr;
}

.view {
  width: 100%;
  height: 100%;
  display: grid;
  place-items: center;
  grid-template-columns: 1fr auto 1fr;
}
</style>
