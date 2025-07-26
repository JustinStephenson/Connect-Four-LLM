<template>
  <section class="main">
    <div class="status">
      <span class="status__text">{{ status }}</span>
      <div class="status__button">
        <Button @click="playAgain()">
          <span>Play Again</span>
        </Button>
      </div>
    </div>
    <div class="view">
      <PlayerCard :image-url="Player_Icon" :token="players.PlayerOne">
        <span>Player 1</span>
      </PlayerCard>
      <Board
        :slot-matrix="board"
        :current-player="players.PlayerOne"
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
import Button from "./common/Button.vue";

const players: Record<string, TokenType> = {
  PlayerOne: 1 as TokenType,
  PlayerTwo: 2 as TokenType,
};

const board = ref<TokenType[][]>(createMatrix(6, 7));
const llmLoading = ref<boolean>(false);
const llmResponse = ref<LLMResponse>(null);
const status = ref<string>("");

const onPlayerPlayed = (playerPos: PlayerPos) => {
  const hasWon = checkForWin(playerPos, players.PlayerOne);
  if (!hasWon) {
    llmTakeTurn(board.value);
  }
};
const checkForWin = (playerPos: PlayerPos, player: TokenType): boolean => {
  const hasWon = checkWin(board.value, player, playerPos.row, playerPos.col);
  if (hasWon) {
    status.value = "Player " + player + " has won!";
    return true;
  }
  return false;
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

  if (jsonResponse != null) {
    placeToken(boardState, players.PlayerTwo, jsonResponse.choice, (row, col) =>
      checkForWin({ row: row, col: col }, players.PlayerTwo),
    );
  }
};
const llmChoice = (choice: number): string => {
  return "Column " + (choice + 1) + "";
};
const playAgain = () => {
  board.value = createMatrix(6, 7);
  llmLoading.value = false;
  llmResponse.value = null;
  status.value = "";
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

.status {
  width: 100%;
  height: 100%;
  @include mixin.flexCenter();
  flex-direction: column;

  &__text {
    transform: translate(0, 50%);
  }

  &__button {
    transform: translate(0, 150%);
  }
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
}
</style>
