# 1단계: 빌드 jdk라는 이미지를 통해 빌드 --> builde로 이름 만듬
FROM gradle:8.7-jdk17 AS build
WORKDIR /app

#이 파일 변경되면 docker캐시 무효화하고 빌드한다.
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# gradlew 스크립트 실행 권한 부여
RUN chmod +x gradlew
# 테스트를 제외하고 애플리케이션을 빌드. 빌드 결과물인 JAR 파일은 /app/build/libs/에 생성됨
RUN ./gradlew clean build -x test

# 2단계: 실행
# 실행을 위한 이미지 생성
FROM eclipse-temurin:17-jdk
WORKDIR /app

# 1단계(build)에서 생성된 JAR 파일을 가져와 app.jar라는 이름으로 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 컨테이너의 8080 포트를 외부에 노출 (컨테이너가 8080 포트를 사용한다고 선언)
EXPOSE 8080

# 컨테이너 시작 시 실행될 명령어로 java -jar app.jar 지정
ENTRYPOINT ["java", "-jar", "app.jar"]

