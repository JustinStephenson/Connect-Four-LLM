import { onBeforeMount } from 'vue';

/**
 * A hook to add a mobile friendly css --vh variable.
 * In CSS use -> height: calc(var(--vh) * {value});
 */
export function useMobileResponsive() {
  onBeforeMount(() => {
    const calculateVh = () => {
      const vh: number = window.innerHeight * 0.01;
      document.documentElement.style.setProperty('--vh', `${vh}px`);
    };

    // Initial calculation
    calculateVh();
    // Re-calculate on resize
    window.addEventListener('resize', calculateVh);
    // Re-calculate on device orientation change
    window.addEventListener('orientationchange', calculateVh);
  });
}
