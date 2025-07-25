<template>
  <div class="card">
    <icon class="card__img" :image-url="props.imageUrl" />
    <slot />
    <div class="card__token">
      <span class="card__token__disc">Disc: </span>
      <Token :number="token"></Token>
    </div>
  </div>
</template>

<script setup lang="ts">
import Icon from "./Icon.vue";
import Token from "../board/Token.vue";
import type { TokenType } from "../../types/types.ts";

type PlayerCardProps = {
  imageUrl: string;
  token: TokenType;
};
const props = defineProps<PlayerCardProps>();
</script>

<style scoped lang="scss">
@use "../../styles/mixins.scss" as mixin;

.card {
  position: relative;
  width: 40rem;
  height: 45rem;
  display: grid;
  grid-template-rows: 5fr 1fr;
  justify-items: center;
  align-items: center;
  border: var(--standard-border);
  border-radius: 1rem;
  box-shadow: -0.5rem 0.5rem 1rem rgba(0, 0, 0, 0.5); // bottom-left shadow

  &__img {
    position: absolute;
    top: -5rem;
    left: 50%;
    transform: translateX(-50%);
    width: 10rem;
    height: 10rem;
    border-radius: 50%;
    margin-bottom: 1rem;
  }

  &__token {
    @include mixin.flexCenter();
    margin: 1rem;

    &__disc {
      margin-right: 4rem;
    }
  }
}
</style>
