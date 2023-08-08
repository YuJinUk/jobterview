# 기본 이미지 선택 (예: Python 3.9)
FROM python:3.8

# 작업 디렉토리 설정
WORKDIR /app

# 필요한 파일 복사
COPY . /app

# 필요한 시스템 패키지를 설치합니다.
RUN apt-get update && \
    apt-get install -y g++ cmake portaudio19-dev libgl1-mesa-glx ffmpeg&& \
    rm -rf /var/lib/apt/lists/*

# 의존성 패키지 설치
RUN pip install -r requirements.txt

# Flask 애플리케이션 실행을 위한 명령
CMD ["python", "flask_with_vue.py"]