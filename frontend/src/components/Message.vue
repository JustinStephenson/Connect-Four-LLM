<template>
  <div class="message">
    <input class="input" v-model="prompt" placeholder="Enter your prompt" />
    <button class="button" @click="sendGetRequest()">GET Request</button>
    <button class="button" @click="sendPostRequest()">POST Request</button>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const message = ref("Loading...");
const prompt = ref("");

const sendGetRequest = async () => {
  const res = await fetch("/server/connect-four/");
  message.value = await res.text();
  console.log("GET response:", message.value);
};

const sendPostRequest = async () => {
  console.log("prompt:", prompt.value);
  const res = await fetch("/server/connect-four/prompt", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      prompt: prompt.value,
    }),
  });
  message.value = await res.text();
  console.log("POST response:", message.value);
};
</script>

<style scoped lang="scss">
.message {
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.button {
  width: 25%;
  height: 25%;
}
</style>
