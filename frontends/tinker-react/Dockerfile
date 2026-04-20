FROM node:22-alpine
WORKDIR /app

COPY package*.json ./

EXPOSE 5173
CMD npm install && npm run dev -- --host 0.0.0.0
