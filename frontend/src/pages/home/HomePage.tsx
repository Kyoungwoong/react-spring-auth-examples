import React from 'react';
import { Link } from 'react-router-dom';
import './HomePage.css';

const HomePage: React.FC = () => {
    return (
        <div className="homepage">
            <header className="homepage-header">
                <h1>Welcome to Our Website</h1>
                <p>Your journey to explore begins here</p>
            </header>
            <section className="introduction">
                <h2>About Us</h2>
                <p>
                    We are committed to providing the best services and solutions to help you achieve your goals.
                    Explore our range of offerings and see how we can assist you.
                </p>
            </section>
            <section className="features">
                <div className="feature-card">
                    <h3>Cookie-Based Login</h3>
                    <p>Learn how to implement login using cookies.</p>
                    <Link to="/login-cookie">Learn More</Link>
                </div>
                <div className="feature-card">
                    <h3>Session-Based Login</h3>
                    <p>Learn how to implement login using sessions.</p>
                    <Link to="/login-session">Learn More</Link>
                </div>
                <div className="feature-card">
                    <h3>JWT-Based Login</h3>
                    <p>Learn how to implement login using spring-security JWT tokens.</p>
                    <Link to="/login-security">Learn More</Link>
                </div>
                <div className="feature-card">
                    <h3>OAuth-Based Login</h3>
                    <p>Learn how to implement login using OAuth 2.0.</p>
                    <Link to="/login-oauth">Learn More</Link>
                </div>
            </section>
        </div>
    );
};

export default HomePage;
