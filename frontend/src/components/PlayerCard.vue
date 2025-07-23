<template>
  <div class="card">
    <slot />
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
@use "../styles/mixins.scss" as mixin;

.card {
  width: 20rem;
  height: 25rem;
  border: var(--standard-border);
  border-radius: 1rem;
  box-shadow: -0.5rem 0.5rem 1rem rgba(0, 0, 0, 0.5); // bottom-left shadow

  @include mixin.flexCenter();
}
</style>
