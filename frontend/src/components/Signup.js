import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import './Signup.css'; // Custom CSS

const Signup = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: ''
  });

  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/auth/register', {
        name: formData.name,
        email: formData.email,
        password: formData.password
      });

      // If registration is successful
      if (response.status === 201) {
        setSuccessMessage('Registration successful! Redirecting to login...');
        setErrorMessage('');

        // Redirect to login page after success
        setTimeout(() => {
          window.location.href = '/login'; // Adjust based on your app structure
        }, 2000);
      }

    } catch (error) {
      // Handle registration errors
      console.error('Registration error:', error);
      setErrorMessage('Registration failed. Please try again.');
    }
  };

  return (
    <div className="signup-container">
      <form className="signup-form" onSubmit={handleSubmit}>
        <h2 className="text-center mb-4">Sign Up</h2>

        {/* Display success or error message */}
        {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
        {successMessage && <div className="alert alert-success">{successMessage}</div>}

        <div className="form-group mb-3">
          <label htmlFor="name">Name</label>
          <input 
            type="text" 
            className="form-control" 
            id="name" 
            name="name" 
            value={formData.name} 
            onChange={handleChange} 
            required 
            placeholder="Enter your name" 
          />
        </div>

        <div className="form-group mb-3">
          <label htmlFor="email">Email address</label>
          <input 
            type="email" 
            className="form-control" 
            id="email" 
            name="email" 
            value={formData.email} 
            onChange={handleChange} 
            required 
            placeholder="Enter your email" 
          />
        </div>

        <div className="form-group mb-3">
          <label htmlFor="password">Password</label>
          <input 
            type="password" 
            className="form-control" 
            id="password" 
            name="password" 
            value={formData.password} 
            onChange={handleChange} 
            required 
            placeholder="Enter your password" 
          />
        </div>

        <button type="submit" className="btn btn-primary w-100 mb-3">Sign Up</button>

        <div className="text-center">
          <p>Already have an account? <Link to="/login">Log in</Link></p>
        </div>
      </form>
    </div>
  );
};

export default Signup;
