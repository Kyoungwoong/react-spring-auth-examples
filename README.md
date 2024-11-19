# react-spring-auth-examples
## 프로젝트 개요

이 레포지토리는 React와 Spring Boot를 이용하여 다양한 로그인 방식을 구현하는 예제 프로젝트입니다. 웹 애플리케이션에서 인증은 중요한 요소이며, 본 프로젝트에서는 쿠키, 세션, JWT 토큰, 그리고 OAuth2를 이용한 로그인 방식을 다룹니다.

## 주요 기능

- **쿠키 기반 인증**: 사용자 정보를 쿠키에 저장하여 인증 상태를 유지하는 방식.
- **세션 기반 인증**: 서버 측에서 세션을 생성하고 세션 ID를 클라이언트 쿠키에 저장하여 인증을 유지하는 방식.
- **JWT 토큰 기반 인증**: 클라이언트와 서버 간의 stateless 통신을 위해 JWT 토큰을 이용한 인증 방식.
- **OAuth2 인증**: Google, Facebook 등과 같은 타사 서비스를 통해 인증을 수행하는 방식.

## 기술 스택

- **프론트엔드**: React
- **백엔드**: Spring Boot
- **데이터베이스**: H2 Database (테스트용)
- **인증 관련 라이브러리**:
    - Spring Security
    - JSON Web Token (JWT)
    - OAuth2 클라이언트 라이브러리

## 프로젝트 구조

```css
multi-authentication-sample/
├── backend/          # Spring Boot 프로젝트
│   └── src/
│       └── main/
│           └── java/
│               └── com.example.auth/
│                   └── controllers/
│                   └── services/
│                   └── config/
│                   └── models/
├── frontend/         # React 프로젝트
│   └── src/
│       └── components/
│       └── pages/
│       └── services/

```

## 시작하기

### 1. 프론트엔드 설정

1. `frontend` 디렉토리로 이동 후 패키지 설치:
    
    ```bash
    cd frontend
    npm install
    ```
    
2. 개발 서버 실행:
    
    ```bash
    npm start
    ```
    

### 2. 백엔드 설정

1. `backend` 디렉토리로 이동 후 Maven 프로젝트 빌드:
    
    ```bash
    cd backend
    ./gradlew build
    ```
    
2. 스프링 부트 애플리케이션 실행:
    
    ```bash
    ./gradlew bootRun
    ```
    

## 사용 예시

1. 브라우저에서 애플리케이션에 접속 (`http://localhost:3000`)
2. 각 인증 방식에 따른 로그인 페이지에서 로그인 시도
3. 로그인 상태 확인 및 로그아웃 기능

## 향후 개선 사항

- 다양한 인증 방식에 대한 테스트 코드 추가
- OAuth2 인증 서비스 확장 (예: GitHub, Twitter)
- 사용자 인증 데이터베이스 연동

## 기여 방법

1. 이 프로젝트를 포크합니다.
2. 새 브랜치를 생성합니다 (`git checkout -b feature/새기능`).
3. 변경 사항을 커밋합니다 (`git commit -m '새 기능 추가'`).
4. 브랜치에 푸시합니다 (`git push origin feature/새기능`).
5. Pull Request를 생성합니다.
