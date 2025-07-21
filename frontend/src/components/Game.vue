<template>
  <section class="main">
    <div class="status">{{ status }}</div>
    <div class="view">
      <div>Player1</div>
      <Board
        :slot-matrix="board"
        :current-player="currentPlayer"
        @player-pos="onPlayedToken"
      />
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Board from "./board/Board.vue";
import { checkWin, createMatrix } from "../util";
import type { PlayerPos, TokenType } from "../types/types.ts";

const board: TokenType[][] = createMatrix(6, 7);
const players: Record<string, TokenType> = {
  PlayerOne: 1 as TokenType,
  PlayerTwo: 2 as TokenType,
};
const currentPlayer = ref<TokenType>(players.PlayerOne);
const status = ref<string>("");

const onPlayedToken = (playerPos: PlayerPos) => {
  const hasWon = checkWin(
    board,
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
    llmTakeTurn(board);
    currentPlayer.value = players.PlayerTwo;
  } else {
    currentPlayer.value = players.PlayerOne;
  }
};
const llmTakeTurn = async (boardState: TokenType[][]) => {
  const res = await fetch("/server/connect-four/board", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      boardState: boardState,
    }),
  });
  const response = await res.text();
  console.log("llm response:", response);
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
