<template>
  <div class="slot">
    <Token :number="props.number" />
  </div>
</template>

<script setup lang="ts">
import Token from "./Token.vue";
import type { TokenType } from "../../types/TokenType.ts";

type SlotProps = {
  number: TokenType;
};
const props = withDefaults(defineProps<SlotProps>(), {
  number: 0,
});
</script>

<style scoped lang="scss">
@use "../../styles/mixins.scss" as mixin;

.slot {
  width: 100%;
  height: 100%;
  border: 0.5rem solid black;
  background-color: transparent;

  @include mixin.flexCenter;
}
</style>
