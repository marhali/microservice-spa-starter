import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import Bootstrap from '@/bootstrap/bootstrap.tsx';
import '@/main.css';

createRoot(document.querySelector('#root')!).render(
  <StrictMode>
    <Bootstrap />
  </StrictMode>,
);
