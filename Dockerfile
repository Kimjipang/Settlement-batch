# 기반 이미지 선택
FROM openjdk:21

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","app.jar"]
