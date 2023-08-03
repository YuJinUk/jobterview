# 기반이 될 Docker 이미지 선택
FROM adoptopenjdk:11-jre-hotspot

# 작업 디렉토리 생성
WORKDIR /app

# 빌드된 jar 파일을 Docker 이미지에 복사
COPY build/libs/jobterview-0.0.1-SNAPSHOT.jar /app/app.jar

# 어플리케이션 실행
CMD ["java", "-jar", "app.jar"]