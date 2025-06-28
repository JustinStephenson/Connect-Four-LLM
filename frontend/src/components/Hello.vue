<template>
  <div>
    {{ message }}
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";

onMounted(async () => {
  const res = await fetch("/server/connect-four/");
  message.value = await res.text();
});

const message = ref("Loading...");
</script>

<style scoped lang="scss"></style>
