import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],

  server: {
    port: 4200,
    host: "localhost",
    proxy: {
      "/server": {
        target: "http://localhost:8080/api/v1/",
        secure: false,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/server/, ""),
      },
    },
  },
});
