# dream_stadium

# 고객이 경기장 예매에 불편함을 겪지 않도록 만든 개인 프로젝트

## ◽ 프로젝트 정보
- 고객이 경기장 예매에 불편함을 겪지 않도록 만든 프로젝트 입니다.
- 개인 프로젝트에서는 팀 프로젝트와 달리 실제 환경에 가까운 조건을 만들기 위해 DBeaver, JMeter, JWT, Spring Security를 적용하여 여러 사용자가 동시에 요청을 보내는 상황을 시뮬레이션했고, 성공적으로 테스트를 마쳤습니다.
-  Docker Compose, EC2, GitHub Actions, GHCR를 활용해 CI/CD 환경을 구축하며 배포 자동화까지 구현했습니다.
-  dream_stadium_V1에서는 예약시스템에서 유저가 예약을 하고 현장에서 쿠폰을 보여줘서 직원이 할인해주는 구조였다. 이러한 구조는 실제 업무와는 거리가 있다고 생각을 해
  예약시스템에서 유저가 예약과 동시에 쿠폰사용여부를 결정하고 바로 할인이 적용되는 구조로 새롭게 코드를 생성하였습니다.

## 🚀 주요 기능
- 좌석 예매
- spring security를 이용한 로그인
- 쿠폰기능
- docker-compose 적용 및 CI/CD 파이프라인 구현
- 부하 테스트
- NABER Oauth2.0 로그인 기능
- 구글 recaptcha 기능


## 🔧 사용 기술
<p align="center">
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white"> 
  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/SpringMVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
  <img src="https://img.shields.io/badge/Redisson-FF0000?style=for-the-badge&logo=redis&logoColor=white">
  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/DockerCompose-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/GHCR-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/AWS%20EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
  <img src="https://img.shields.io/badge/GitHubActions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white">
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/JMeter-D22128?style=for-the-badge&logo=apachejmeter&logoColor=white">
</p>

## ⏰ 프로젝트 기간
- 2025.9.10 ~ 2025. 10. 10 (약 4주)

## 🧑‍💻 팀원 소개
| 이름       | 역할   | 담당 업무                  |
|-----------|--------|-------------------------|
| 김현준      |  팀장 | 전체기능| 


## 🔗 와이어프레임
- [와이어 프레임 링크](https://www.figma.com/design/XfzXpl6Q6GOpBJrvhrFGiW/Untitled?node-id=0-1&p=f&m=draw)

## 💻 ERD
- [ERD 링크](https://www.erdcloud.com/d/HwkrJhhb3vdgXWRGD)
  
## 📑 API 명세서
- [API 명세서](https://docs.google.com/spreadsheets/d/11Qq5HuWawa5z2Xr85AcOYGalAq86K45NjR2TovMdyxE/edit?pli=1&gid=949175960#gid=949175960)

## 📜 트러블 슈팅 및 정리
- [Oauth2.0 정리](https://qlgh2gh.tistory.com/73)
- [구글 recaptcha](https://qlgh2gh.tistory.com/74)
- [orphanRemoval = true 오류](https://qlgh2gh.tistory.com/54)
- [유저쿠폰 다운로드 문제점](https://qlgh2gh.tistory.com/57)
- [UserCoupon 수정](https://qlgh2gh.tistory.com/77)
- [알림 문제](https://qlgh2gh.tistory.com/58)
- [Jmeter(분산락, security 테스트 ) + Jmeter 설정문제](https://qlgh2gh.tistory.com/59)
- [jmeter(새로운 예약 시스템 적용) - 부하 테스트 락x](https://qlgh2gh.tistory.com/79)
- [jmeter(새로운 예약 시스템 적용) - 부하 테스트 낙관락/분산락](https://qlgh2gh.tistory.com/80)
- [배포 과정 GHCR + git hub 무중단 배포(chapter 1) ](https://qlgh2gh.tistory.com/82)
- [깃허브 ci/cd + nginx + https:// 설정 (chapter 2)](https://qlgh2gh.tistory.com/83)
---
