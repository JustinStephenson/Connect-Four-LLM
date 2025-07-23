<template>
  <div class="container">
    <div class="loading"></div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
@keyframes spinner {
  0% {
    rotate: 0deg;
  }
  100% {
    rotate: 360deg;
  }
}

.container {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.loading {
  width: 10rem;
  height: 10rem;
  border: 1.5rem solid white;
  border-top: 1.5rem solid black;
  border-radius: 50%;
  animation: spinner 1.5s linear infinite;
}
</style>
