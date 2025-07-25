<template>
  <section class="main">
    <div class="status">{{ status }}</div>
    <div class="view">
      <PlayerCard :image-url="Player_Icon" :token="players.PlayerOne">
        <span>Player 1</span>
      </PlayerCard>
      <Board
        :slot-matrix="board"
        :current-player="currentPlayer"
        @player-pos="onPlayerPlayed"
      />
      <PlayerCard :image-url="AI_Icon" :token="players.PlayerTwo">
        <div v-if="llmLoading">
          <Spinner />
          <br />
          <span>Thinking...</span>
        </div>
        <div v-else-if="llmResponse" class="response">
          <div class="response__reason">
            <span>Reasoning:</span>
            <span>{{ llmResponse.reasoning }}</span>
          </div>
          <div class="response__choice">
            Choice: {{ llmChoice(llmResponse.choice) }}
          </div>
        </div>
        <div v-else>Player 2</div>
      </PlayerCard>
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Board from "./board/Board.vue";
import Spinner from "./common/Spinner.vue";
import PlayerCard from "./common/PlayerCard.vue";
import AI_Icon from "../assets/AI_icon.png";
import Player_Icon from "../assets/Player_icon.png";
import { checkWin, createMatrix, placeToken } from "../util";
import type { LLMResponse, PlayerPos, TokenType } from "../types/types.ts";

const players: Record<string, TokenType> = {
  PlayerOne: 1 as TokenType,
  PlayerTwo: 2 as TokenType,
};

const board = ref<TokenType[][]>(createMatrix(6, 7));
const currentPlayer = ref<TokenType>(players.PlayerOne);
const llmLoading = ref<boolean>(false);
const llmResponse = ref<LLMResponse>(null);
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

  llmLoading.value = false;
  llmResponse.value = jsonResponse;

  placeToken(boardState, players.PlayerTwo, jsonResponse.choice);
};
const llmChoice = (choice: number): string => {
  return "Column " + (choice + 1) + "";
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

.response {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 2fr 1fr;
  justify-items: center;
  align-items: center;
  padding: 1.5rem;

  &__reason {
    @include mixin.flexCenter();
    flex-direction: column;
    gap: 1rem;
  }

  &__choice {
  }
}
</style>
