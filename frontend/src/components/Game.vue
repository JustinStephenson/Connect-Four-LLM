<template>
  <section class="main">
    <div class="status">{{ status }}</div>
    <div class="view">
      <PlayerCard>
        <span>Player1</span>
      </PlayerCard>
      <Board
        :slot-matrix="board"
        :current-player="currentPlayer"
        @player-pos="onPlayerPlayed"
      />
      <PlayerCard>
        <div v-if="llmLoading">
          <Spinner />
          <br />
          <span>Thinking...</span>
        </div>
        <div v-else>LLM</div>
      </PlayerCard>
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Board from "./board/Board.vue";
import Spinner from "./Spinner.vue";
import { checkWin, createMatrix, placeToken } from "../util";
import type { LLMResponse, PlayerPos, TokenType } from "../types/types.ts";
import PlayerCard from "./PlayerCard.vue";

const players: Record<string, TokenType> = {
  PlayerOne: 1 as TokenType,
  PlayerTwo: 2 as TokenType,
};

const board = ref<TokenType[][]>(createMatrix(6, 7));
const currentPlayer = ref<TokenType>(players.PlayerOne);
const llmLoading = ref<boolean>(false);
const status = ref<string>("");

const onPlayerPlayed = (playerPos: PlayerPos) => {
  const hasWon = checkWin(
    board.value,
    currentPlayer.value,
    playerPos.row,
    playerPos.col,
  );
  if (hasWon) {
    status.value = "Player " + currentPlayer.value + " has won!";
  } else {
    llmTakeTurn(board.value);
  }
};
const llmTakeTurn = async (boardState: TokenType[][]) => {
  llmLoading.value = true;
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
  const jsonResponse = JSON.parse(response) as LLMResponse;
  console.log(jsonResponse);
  llmLoading.value = false;
  placeToken(boardState, players.PlayerTwo, jsonResponse.choice);
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
