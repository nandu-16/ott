import React, { useState } from 'react';
import { Container, Form, Button, Alert } from 'react-bootstrap';

const ChangePassword = () => {
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleUpdatePassword = (e) => {
    e.preventDefault();

    // Simple password matching check
    if (newPassword !== confirmPassword) {
      setError("New password and confirmation do not match.");
      setSuccess('');
    } else {
      // Simulate password update logic
      setSuccess('Your password has been updated successfully!');
      setError('');
      setCurrentPassword('');
      setNewPassword('');
      setConfirmPassword('');
    }
  };

  return (
    <div style={styles.background}>
      <Container style={styles.container}>
        <h2 className="text-center" style={styles.heading}>Change Password</h2>

        <Form onSubmit={handleUpdatePassword}>
          {/* Current Password */}
          <Form.Group controlId="formCurrentPassword">
            <Form.Label style={styles.label}>Current Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Enter current password"
              value={currentPassword}
              onChange={(e) => setCurrentPassword(e.target.value)}
              style={styles.input}
              required
            />
          </Form.Group>

          {/* New Password */}
          <Form.Group controlId="formNewPassword">
            <Form.Label style={styles.label}>New Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Enter new password"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              style={styles.input}
              required
            />
          </Form.Group>

          {/* Confirm New Password */}
          <Form.Group controlId="formConfirmPassword">
            <Form.Label style={styles.label}>Confirm New Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Confirm new password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              style={styles.input}
              required
            />
          </Form.Group>

          {/* Update Button */}
          <Button variant="warning" type="submit" style={styles.button}>
            Update Password
          </Button>
        </Form>

        {/* Alert Messages */}
        {error && <Alert variant="danger" className="mt-4">{error}</Alert>}
        {success && <Alert variant="success" className="mt-4">{success}</Alert>}
      </Container>
    </div>
  );
};

// Inline CSS styles
const styles = {
  background: {
    backgroundImage: 'url("https://img.freepik.com/free-photo/abstract-grunge-decorative-relief-navy-blue-stucco-wall-background_1258-111317.jpg")',
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

export default ChangePassword;
