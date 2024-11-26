import React, { useState, useEffect } from 'react';
import SuccessPage from '../successlogin/SuccessPage';
import './Session.css';

const SessionLogin: React.FC = () => {
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [error, setError] = useState<string>('');
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

  // Check session status when the component mounts
  useEffect(() => {
    const checkSession = async () => {
      try {
        const response = await fetch(`${process.env.REACT_APP_BASE_URL}/session/check-login`, {
          method: 'GET',
          credentials: 'include', // Include cookies in the request
        });

        if (response.ok) {
          setIsAuthenticated(true);
        } else {
          setIsAuthenticated(false);
        }
      } catch (error) {
        console.error('Error checking session:', error);
        setIsAuthenticated(false);
      }
    };

    checkSession();
  }, []);

  // Handle login
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await fetch(`${process.env.REACT_APP_BASE_URL}/session/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        setIsAuthenticated(true);
        alert('Login successful');
      } else {
        setError('Invalid username or password');
      }
    } catch (error) {
      setError('An error occurred while logging in');
    }
  };

  // Handle logout
  const handleLogout = async () => {
    try {
      const response = await fetch(`${process.env.REACT_APP_BASE_URL}/session/logout`, {
        method: 'GET',
        credentials: 'include',
      });

      if (response.ok) {
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
        <h2 className="login-title">Login with Session</h2>
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

export default SessionLogin;
