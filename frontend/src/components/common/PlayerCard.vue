<template>
  <div class="card">
    <icon class="card__img" :image-url="props.imageUrl" />
    <slot />
  </div>
</template>

<script setup lang="ts">
import Icon from "./Icon.vue";

type PlayerCardProps = {
  imageUrl: string;
};
const props = defineProps<PlayerCardProps>();
</script>

<style scoped lang="scss">
@use "../../styles/mixins.scss" as mixin;

.card {
  position: relative;
  width: 20rem;
  height: 25rem;
  border: var(--standard-border);
  border-radius: 1rem;
  box-shadow: -0.5rem 0.5rem 1rem rgba(0, 0, 0, 0.5); // bottom-left shadow

  @include mixin.flexCenter();

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
}
</style>
