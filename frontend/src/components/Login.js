import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import "./login.css";

const Login = () => {
  const [username, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const navigate = useNavigate();

  // Handle login form submission
  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        username,
        password
      });

      // If login is successful
      if (response.status === 200) {
        const userId= response.data.user.userId // Assuming response contains userId and token
         const token = response.data.jwt
        // Store userId and token in localStorage
        localStorage.setItem('user', userId);
        localStorage.setItem('token', token);

        setSuccessMessage('Login successful! Redirecting to homepage...');
        setErrorMessage('');

        // Redirect to the home page after successful login
        setTimeout(() => {
          navigate('/home');
        }, 2000);
      } else {
        setErrorMessage('Invalid credentials. Please try again.');
      }

    } catch (error) {
      console.error('Login failed:', error);
      setErrorMessage('Invalid email or password. Please try again.');
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card transparent-card">
            <div className="card-header text-center">
              <h3>Login</h3>
            </div>
            <div className="card-body">
              {/* Show success or error message */}
              {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
              {successMessage && <div className="alert alert-success">{successMessage}</div>}

              <form onSubmit={handleLogin}>
                <div className="form-group mb-3">
                  <label htmlFor="email">Email address</label>
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    placeholder="Enter email"
                    value={username}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group mb-3">
                  <label htmlFor="password">Password</label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary w-100">
                  Login
                </button>
              </form>
            </div>
            <div className="card-footer text-center">
              <p>Don't have an account? <a href="/Signup">Signup</a></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
