// <reference type="vitest" />
import { defineConfig } from 'vite';
import tsconfigPaths from 'vite-tsconfig-paths';
import react from '@vitejs/plugin-react-swc';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [tsconfigPaths(), react()],
  server: {
    proxy: {
      '/auth': {
        target: 'http://localhost:8089',
        xfwd: true,
      },
      '/api': {
        target: 'http://localhost:8080',
        xfwd: true,
      },
      '/trace': {
        target: 'https://localhost:8200',
        rewrite: (path) => path.slice(6),
        secure: false,
      },
    },
  },
});
