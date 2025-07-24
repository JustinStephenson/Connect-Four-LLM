<template>
  <div class="dropdown">
    <button @click="toggleDropdown" class="dropdown-button">
      {{ selectedLabel || placeholder }}
    </button>
    <ul v-if="isOpen" class="dropdown-menu">
      <li
        v-for="option in options"
        :key="option.value"
        @click="selectOption(option)"
        class="dropdown-item"
      >
        {{ option.label }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, defineEmits } from "vue";

export type Options = {
  label: string;
  value: string | number;
};
export type DropdownProps = {
  options: Options[];
  modelValue?: string | number;
  placeholder?: string;
};
const props = defineProps<DropdownProps>();

const emit = defineEmits(["update:modelValue"]);

const isOpen = ref(false);
const selectedLabel = ref<string | null>(null);

function toggleDropdown() {
  isOpen.value = !isOpen.value;
}

function selectOption(option: { label: string; value: string | number }) {
  emit("update:modelValue", option.value);
  selectedLabel.value = option.label;
  isOpen.value = false;
}

// Update label if value is set externally
watch(
  () => props.options,
  () => {
    const selected = props.options.find((o) => o.value === props.modelValue);
    selectedLabel.value = selected?.label ?? null;
  },
  { immediate: true },
);
</script>

<style scoped lang="scss">
.dropdown {
  position: relative;
  display: inline-block;
  width: 200px;
}

.dropdown-button {
  font-size: var(--base-font-size);
  width: 100%;
  padding: 0.5rem;
  text-align: left;
  background: white;
  border: 1px solid #ccc;
  cursor: pointer;
}

.dropdown-menu {
  position: absolute;
  width: 100%;
  margin: 0;
  padding: 0;
  list-style: none;
  background: white;
  border: 1px solid #ccc;
  z-index: 1000;
}

.dropdown-item {
  font-size: var(--base-font-size);
  padding: 0.5rem;
  cursor: pointer;

  &:hover {
    background-color: #f3f3f3;
  }
}
</style>
