import React from 'react';
import './OAuth2Login.css';

const OAuth2Login = () => {
  return (
    <div className="oauth-container">
      <h2 className="oauth-title">OAuth2 Login</h2>
      <a
        href="http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:3000&mode=login"
        className="oauth-button google-button"
      >
        Login with Google
      </a>
      <a
        href="http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:3000&mode=login"
        className="oauth-button naver-button"
      >
        Login with Naver
      </a>
      <a
        href="http://localhost:8080/oauth2/authorization/kakao?redirect_uri=http://localhost:3000&mode=login"
        className="oauth-button kakao-button"
      >
        Login with Kakao
      </a>
    </div>
  );
};

export default OAuth2Login;
