# Node.js 이미지를 사용하여 기본 이미지를 설정합니다.
FROM node:14

# 작업 디렉토리를 생성합니다. (여기서는 /app으로 설정하였습니다.)
WORKDIR /app

# 호스트의 현재 디렉토리에 있는 package.json과 package-lock.json을 컨테이너의 작업 디렉토리로 복사합니다.
COPY package*.json ./

# npm 패키지를 설치합니다.
RUN npm install

# 호스트의 현재 디렉토리의 모든 파일과 디렉토리를 컨테이너의 작업 디렉토리로 복사합니다.
COPY . .

# Vue CLI 개발 서버를 실행합니다. (또는 npm run serve 등을 사용할 수 있습니다.)
CMD ["npm", "run", "serve"]