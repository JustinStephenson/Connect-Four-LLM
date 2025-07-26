<template>
  <button
    :class="['base-button', props.variant]"
    :disabled="props.disabled"
    @click="props.onClick"
  >
    <slot />
  </button>
</template>

<script setup lang="ts">
export type ButtonProps = {
  variant?: "primary" | "secondary" | "danger";
  disabled?: boolean;
  onClick?: () => void;
};
const props = defineProps<ButtonProps>();
</script>

<style scoped lang="scss">
.base-button {
  font-size: var(--base-font-size);
  padding: 0.5rem 1rem;
  font-weight: 600;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

/* Variants */
.primary {
  background-color: #007bff;
  color: white;
}

.primary:hover:not(:disabled) {
  background-color: #0056b3;
}

.secondary {
  background-color: #6c757d;
  color: white;
}

.secondary:hover:not(:disabled) {
  background-color: #5a6268;
}

.danger {
  background-color: #dc3545;
  color: white;
}

.danger:hover:not(:disabled) {
  background-color: #b02a37;
}

/* Disabled state */
button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
