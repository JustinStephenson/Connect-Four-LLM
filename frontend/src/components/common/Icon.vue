<template>
  <div class="image-container">
    <img :src="props.imageUrl" alt="Image" class="image" />
  </div>
</template>

<script setup lang="ts">
type IconProps = {
  imageUrl: string;
};
const props = defineProps<IconProps>();
</script>

<style scoped lang="scss">
@use "../../styles/mixins.scss" as mixin;

.image-container {
  @include mixin.flexCenter();
}

.image {
  max-width: 100%;
  height: auto;
  border-radius: 0.5rem;
}
</style>
