<template>
  <section class="header">
    <div class="llm">
      <span class="llm__text">Choose an LLM:</span>
      <Dropdown :options="options" :placeholder="options[0].label" />
    </div>
  </section>
</template>

<script setup lang="ts">
import Dropdown, { type Options } from "../common/Dropdown.vue";

const options: Options[] = [
  {
    label: "test1",
    value: "test1",
  },
  {
    label: "test2",
    value: "test2",
  },
];
</script>

<style scoped lang="scss">
@use "../../styles/mixins.scss" as mixin;

.header {
  border: 0.5rem solid black;

  @include mixin.flexCenter();
}

.llm {
  @include mixin.flexCenter();
  flex-direction: column;

  &__text {
    font-size: inherit;
    align-self: flex-start;
  }
}
</style>
