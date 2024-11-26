import React, { useState } from 'react';
import SuccessPage from '../successlogin/SuccessPage';
import './Session.css';

const SessionLogin: React.FC = () => {
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
  const [loginMessage, setLoginMessage] = useState('');
  const [logoutMessage, setLogoutMessage] = useState('');

  // Handle login
  const loginV1 = async () => {
    try {
        const response = await fetch('http://localhost:8080/session/loginV1', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                username: username,
                password: password
            })
        });

        if (response.ok) {
            const data = await response.text(); // 서버의 응답 메시지
            setLoginMessage(data);
            setIsAuthenticated(true);
        } else {
            const errorData = await response.text();
            setLoginMessage('Login failed: ' + errorData);
        }
    } catch (error) {
        setLoginMessage('Login failed: ' + error);
    }
  };

  // 로그인 V2 (세션 객체로 로그인)
  const loginV2 = async () => {
      try {
          const response = await fetch('http://localhost:8080/session/loginV2', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/x-www-form-urlencoded',
              },
              body: new URLSearchParams({
                  username: username,
                  password: password
              })
          });

          if (response.ok) {
              const data = await response.text(); // 서버의 응답 메시지
              setLoginMessage(data);
              setIsAuthenticated(true);
          } else {
              const errorData = await response.text();
              setLoginMessage('Login failed: ' + errorData);
          }
      } catch (error) {
          setLoginMessage('Login failed: ' + error);
      }
  };

  // 로그아웃 V1 (세션 만료)
  const logoutV1 = async () => {
      try {
          const response = await fetch('http://localhost:8080/session/logoutV1', {
              method: 'POST'
          });

          if (response.ok) {
              const data = await response.text(); // 서버의 응답 메시지
              setLogoutMessage(data);
              setIsAuthenticated(false);
          } else {
              const errorData = await response.text();
              setLogoutMessage('Logout failed: ' + errorData);
          }
      } catch (error) {
          setLogoutMessage('Logout failed: ' + error);
      }
  };

  // 로그아웃 V2 (세션 무효화)
  const logoutV2 = async () => {
      try {
          const response = await fetch('http://localhost:8080/session/logoutV2', {
              method: 'POST'
          });

          if (response.ok) {
              const data = await response.text(); // 서버의 응답 메시지
              setLogoutMessage(data);
              setIsAuthenticated(false);
          } else {
              const errorData = await response.text();
              setLogoutMessage('Logout failed: ' + errorData);
          }
      } catch (error) {
          setLogoutMessage('Logout failed: ' + error);
      }
  };

  // Render success page if authenticated
  if (isAuthenticated) {
    return (
      <div>
        <SuccessPage />
        <div className="logout-button">
            <button id="logout-v1-button" onClick={logoutV1}>Logout V1 (Expire Session)</button>
            <button id="logout-v2-button" onClick={logoutV2}>Logout V2 (Invalidate Session)</button>
        </div>
        {logoutMessage && <p className="error-message">{logoutMessage}</p>}
      </div>
    );
  }

  return (
    <div id="login-container">
        <div id="login-form">
            <h2 id="login-title">Session Login</h2>
            <div>
                <label>Username:</label>
                <input 
                    type="text" 
                    className="input-field" 
                    value={username} 
                    onChange={(e) => setUsername(e.target.value)} 
                    placeholder="Enter your username" 
                />
            </div>
            <div>
                <label>Password:</label>
                <input 
                    type="password" 
                    className="input-field" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    placeholder="Enter your password" 
                />
            </div>
            <div>
                <button id="login-v1-button" onClick={loginV1}>Login V1 (Session Cookie)</button>
                <button id="login-v2-button" onClick={loginV2}>Login V2 (Session Attribute)</button>
            </div>
            {loginMessage && <p className="error-message">{loginMessage}</p>}
        </div>
    </div>
  );
};

export default SessionLogin;
