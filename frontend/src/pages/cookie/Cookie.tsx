import React, { useState, useEffect } from 'react';
import SuccessPage from '../successlogin/SuccessPage';
import './Cookie.css';
import { useCookies } from 'react-cookie';

const Cookie: React.FC = () => {
  const [cookies, setCookie, removeCookie] = useCookies(['auth-token']);
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [error, setError] = useState<string>('');
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

  // Check if the user is authenticated based on the "auth-token" cookie
  useEffect(() => {
    const token = cookies['auth-token'];
    setIsAuthenticated(!!token); // Set authentication status based on cookie presence
  }, [cookies]);

  // Login API
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await fetch(
        `${process.env.REACT_APP_BASE_URL}/cookie/login?username=${username}&password=${password}`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        }
      );

      if (response.ok) {
        const token = await response.text(); // Assume the backend returns a token as plain text
        setCookie('auth-token', token, { path: '/', maxAge: 3600 }); // Set the token in cookies
        setIsAuthenticated(true);
        alert('Login successful');
      } else {
        setError('Invalid username or password');
      }
    } catch (error) {
      setError('An error occurred while logging in');
    }
  };

  // Logout API
  const handleLogout = async () => {
    try {
      const response = await fetch(`${process.env.REACT_APP_BASE_URL}/cookie/logout`, {
        method: 'GET',
      });

      if (response.ok) {
        removeCookie('auth-token', { path: '/' }); // Remove the token cookie
        setIsAuthenticated(false);
        alert('Logout successful');
      } else {
        console.error('Logout failed');
      }
    } catch (error) {
      console.error('Logout error:', error);
    }
  };

  // Render success page if authenticated
  if (isAuthenticated) {
    return (
      <div>
        <SuccessPage />
        <button onClick={handleLogout} className="logout-button">
          Logout
        </button>
      </div>
    );
  }

  return (
    <div className="login-container">
      <div className="login-form">
        <h2 className="login-title">Login with Cookie</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              className="input-field"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div>
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              className="input-field"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="login-button">
            Login
          </button>
        </form>
        {error && <p className="error-message">{error}</p>}
      </div>
    </div>
  );
};

export default Cookie;
