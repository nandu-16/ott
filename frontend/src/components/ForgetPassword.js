import React, { useState } from 'react';
import { Container, Form, Button, Alert } from 'react-bootstrap';
import axios from 'axios'; // Import Axios
import { useNavigate } from 'react-router-dom'; // To navigate to the OTP page

const ForgetPassword = () => {
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate(); // Using React Router to navigate

  const handleSendEmail = async (e) => {
    e.preventDefault();

    try {
      // Sending email to backend
      const response = await axios.post('http://localhost:8080/auth/updatePassword', {
        email: email
      });

      if (response.status === 200) {
        // Success
        setMessage(`An email has been sent to ${email} with password reset instructions.`);
        setError('');
        setEmail(''); // Clear the email field
        
        // Redirect to OTP page
        navigate('/otp-verification', { state: { email: email } });
      } else {
        setError('Failed to send email. Please try again.');
        setMessage('');
      }
    } catch (error) {
      setError('Error: Unable to send email. Please check your email and try again.');
      setMessage('');
    }
  };

  return (
    <div style={styles.background}>
      <Container style={styles.container}>
        <h2 className="text-center" style={styles.heading}>Forget Password</h2>
        
        <Form onSubmit={handleSendEmail}>
          <Form.Group controlId="formEmail">
            <Form.Label style={styles.label}>Enter Your Registered Email</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              style={styles.input}
              required
            />
          </Form.Group>

          <Button variant="warning" type="submit" style={styles.button}>
            Send Email
          </Button>
        </Form>

        {message && <Alert variant="success" className="mt-4">{message}</Alert>}
        {error && <Alert variant="danger" className="mt-4">{error}</Alert>}
      </Container>
    </div>
  );
};

// Inline CSS styles
const styles = {
  background: {
    backgroundImage: 'url("https://img.freepik.com/free-photo/yellow-orange-gradient-blurred-background_53876-125688.jpg")',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  container: {
    backgroundColor: 'rgba(255, 255, 255, 0.9)',
    padding: '30px',
    borderRadius: '10px',
    boxShadow: '0px 0px 20px rgba(0, 0, 0, 0.1)',
    maxWidth: '400px',
    width: '100%',
  },
  heading: {
    color: '#ffc107',
    marginBottom: '20px',
  },
  label: {
    color: '#343a40',
    fontWeight: 'bold',
  },
  input: {
    padding: '10px',
    borderRadius: '5px',
    border: '1px solid #ced4da',
    marginBottom: '20px',
  },
  button: {
    width: '100%',
    padding: '10px',
    backgroundColor: '#ffc107',
    border: 'none',
    color: '#fff',
    fontWeight: 'bold',
  },
};

export default ForgetPassword;
