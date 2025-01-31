# 정산 프로젝트

#### 📅 2024.06.19 ~ 2024.07.16

<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/Spring Batch-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<br>
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"><img src="https://img.shields.io/badge/Github Actions-2088FF?style=for-the-badge&logo=Github Actions&logoColor=white"><img src="https://img.shields.io/badge/AWS EC2-FF9900?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/AWS Route 53-8C4FFF?style=for-the-badge&logo=Spring Boot&logoColor=white">

## 💡 프로젝트

<h2 align="center">대용량의 동영상 & 광고 데이터에 대한 통계 및 정산 시스템</h2>

## 🫧 주요 기능 
| **기능**          | **세부 기능**                                          |
|-------------|------------------------------------------------|
| **유저 관리**       | 👤 **회원 가입**<br/> 🔐 **로그인**<br/> 🚪 **로그아웃**  |
| **동영상 & 광고 관리** | 🎥 **동영상** : 등록, 재생, 정지<br/> 📢 **광고** : 등록, 배치 |
| **통계 기능**       | 📊 **동영상 및 광고 통계 조회** : 일간/주간/월간 Top 5         |
| **정산 기능** | 💰 **동영상 및 광고 정산 조회** : 일간/주간/월간             |


## 💥 프로젝트 주요 경험

<h2 align="center">1. 배치 작업 성능 개선 </h2>


### 성능 개선 일지  [문서](https://www.naver.com)


<img src="https://github.com/user-attachments/assets/46f95a0d-27ba-4c89-a66d-c7f90f1a60ca" width="600" height="400">

<h2 align="center">2. 부하 분산 </h2>

### CQRS 패턴 적용
- 쓰기 DB와 읽기 DB 책임의 분리
- Slave DB를 통한 읽기 작업 부하 분산 및 가용성 향상

### DB Master-Slave 구조
| **구분** | **역할** | **특징**     |
| ----- | ------|------------|
| **Master DB** | 쓰기 작업 담당 | 데이터 일관성 보장 |
| **Slave DB** | 읽기 작업 담당 | 조회 성능 최적화 |

<h2 align="center">3. 대용량 데이터 삽입 속도 개선 (기존 대비 약 70% 향상)</h2>

| **단계**   | **데이터 규모** | **소요 시간** | **개선율** |
|----------| ------ | -----| -----|
| **개선 전** | 5천만 건 | 약 25분 14초 | 0% |
| **개선 후** | 5천만 건 | 약 7분 18초 | + 70% |



## 🍀 주요 기술 스택

<img src="https://img.shields.io/badge/Java-41454A?style=for-the-badge&logo=&logoColor=white" height="20" width="43"><img src="https://img.shields.io/badge/21-006600?style=for-the-badge&logo=&logoColor=white" height="20" width="35">
<img src="https://img.shields.io/badge/Spring Boot-41454A?style=for-the-badge&logo=Spring Boot&logoColor=white" height="20" width="110"><img src="https://img.shields.io/badge/3.3.1-6DB33F?style=for-the-badge&logoColor=white" height="20" width="45">
<img src="https://img.shields.io/badge/Spring Batch-41454A?style=for-the-badge&logo=Spring&logoColor=white" height="20" width="120"><img src="https://img.shields.io/badge/5.0-6DB33F?style=for-the-badge&&logoColor=white" height="20" width="35">
<img src="https://img.shields.io/badge/Spring Security-41454A?style=for-the-badge&logo=Spring Security&logoColor=white" height="20" width="120"><img src="https://img.shields.io/badge/6.3.1-6DB33F?style=for-the-badge&&logoColor=white" height="20" width="45">
<img src="https://img.shields.io/badge/Gradle-41454A?style=for-the-badge&logo=Gradle&logoColor=white" height="20" width="67"><img src="https://img.shields.io/badge/8.8-02303A?style=for-the-badge&logoColor=white" height="20" width="36">
<img src="https://img.shields.io/badge/MySQL-41454A?style=for-the-badge&logo=MySQL&logoColor=white" height="20" width="70"><img src="https://img.shields.io/badge/8.3.0-4479A1?style=for-the-badge&logoColor=white" height="20" width="50">