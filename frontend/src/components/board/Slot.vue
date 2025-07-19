<template>
  <div class="slot" :style="{ backgroundColor: props.color }">
    <Token />
  </div>
</template>

<script setup lang="ts">
import Token from "./Token.vue";

type SlotProps = {
  color?: string;
};
const props = withDefaults(defineProps<SlotProps>(), {
  color: "transparent",
});
</script>

<style scoped lang="scss">
@use "../../styles/mixins.scss" as mixin;

.slot {
  width: 100%;
  height: 100%;
  border: 0.5rem solid black;

  @include mixin.flexCenter;
}
</style>
