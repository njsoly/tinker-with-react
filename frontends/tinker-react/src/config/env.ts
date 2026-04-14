export const config = {
  resistorsApiUrl: import.meta.env.VITE_RESISTORS_API_URL || 'http://localhost:8081',
} as const;
