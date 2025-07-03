<template>
  <div class="slot" :style="{ backgroundColor: props.color }"></div>
</template>

<script setup lang="ts">
type SlotProps = {
  color?: string;
};
const props = withDefaults(defineProps<SlotProps>(), {
  color: "transparent",
});
</script>

<style scoped lang="scss">
.slot {
  width: 100%;
  height: 100%;
  border: 0.5rem solid black;
}
</style>
