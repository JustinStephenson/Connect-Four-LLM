<template>
  <div
    class="token"
    :class="{ token__one: props.number === 1, token__two: props.number === 2 }"
  ></div>
</template>

<script setup lang="ts">
import type { Types } from "../../types/types.ts";

type TokenProps = {
  number: Types;
};
const props = defineProps<TokenProps>();
</script>

<style scoped lang="scss">
.token {
  width: 5rem;
  aspect-ratio: 1 / 1;
  border-radius: 50%;
}

.token__one {
  background-color: var(--token-color-player-one);
}

.token__two {
  background-color: var(--token-color-player-two);
}
</style>
