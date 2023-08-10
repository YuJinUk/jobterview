# 기본 이미지 선택 (예: Python 3.9)
FROM node:14.17.0-slim

# 작업 디렉토리 설정
WORKDIR /app

# 필요한 파일 복사
COPY . /app

# npm 패키지 설치
RUN npm install

# node 애플리케이션 실행을 위한 명령
CMD ["npm", "run","serve"]
