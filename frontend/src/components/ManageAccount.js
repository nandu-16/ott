import React, { useState } from 'react';
import { Container, Form, Button, Modal, ListGroup } from 'react-bootstrap';
import CommonNavbar from './CommonNavbar';

const ManageAccount = () => {
  const [showModal, setShowModal] = useState(false);
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  // Sample account details
  const accountDetails = {
    username: "Nandu P.R.",
    subscribedPlan: {
      title: "Standard Plan",
      price: "$14.99/month",
      features: ["1080p Full HD", "2 screens", "Watch on any device"]
    },
    sessions: [
      { date: '2024-01-01', device: 'iPhone', location: 'New York' },
      { date: '2024-01-05', device: 'Laptop', location: 'Los Angeles' },
      { date: '2024-02-01', device: 'Tablet', location: 'Chicago' },
    ]
  };

  const watchLater = ['Movie A', 'Movie B', 'Movie C'];
  const watchHistory = ['Movie D', 'Movie E', 'Movie F'];

  const handlePasswordChange = (e) => {
    e.preventDefault();

    if (newPassword !== confirmPassword) {
      setError('New password and confirmation do not match.');
      return;
    }

    // Here you would normally update the password via an API
    setSuccess('Password changed successfully!');
    setError('');
    handleCloseModal();
  };

  const handleOpenModal = () => setShowModal(true);
  const handleCloseModal = () => {
    setShowModal(false);
    setCurrentPassword('');
    setNewPassword('');
    setConfirmPassword('');
    setError('');
    setSuccess('');
  };

  return (

    <>
      <CommonNavbar/>
      <div style={styles.backgroundImage}>
        <div style={styles.overlay}></div> {/* Transparent overlay */}
        <Container className="py-5" style={styles.container}>
          <h2 className="text-center mb-4" style={{ color: '#ffc107' }}>Manage Account</h2>
        

          {/* Account Details Section */}
          <h3 className="mb-4" style={{ color: '#343a40' }}>Account Details</h3>
          <div className="mb-4">
            <ListGroup>
              <ListGroup.Item>
                <strong>Username:</strong> {accountDetails.username}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Subscribed Plan:</strong> {accountDetails.subscribedPlan.title} - {accountDetails.subscribedPlan.price}
                <ul>
                  {accountDetails.subscribedPlan.features.map((feature, index) => (
                    <li key={index}>{feature}</li>
                  ))}
                </ul>
              </ListGroup.Item>
            </ListGroup>
          </div>

          {/* Sessions Section */}
          <div className="mb-4">
            <h5 className="text-muted">Sessions</h5>
            <ListGroup>
              {accountDetails.sessions.map((session, index) => (
                <ListGroup.Item key={index}>
                  <strong>Date:</strong> {session.date}, <strong>Device:</strong> {session.device}, <strong>Location:</strong> {session.location}
                </ListGroup.Item>
              ))}
            </ListGroup>
          </div>

          {/* Change Password Section */}
          <Button variant="warning" className="mb-4" onClick={handleOpenModal}>
            Change Password
          </Button>

          {/* Modal for Change Password */}
          <Modal show={showModal} onHide={handleCloseModal} centered>
            <Modal.Header closeButton>
              <Modal.Title>Change Password</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form onSubmit={handlePasswordChange}>
                <Form.Group controlId="currentPassword">
                  <Form.Label>Current Password</Form.Label>
                  <Form.Control
                    type="password"
                    value={currentPassword}
                    onChange={(e) => setCurrentPassword(e.target.value)}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="newPassword">
                  <Form.Label>New Password</Form.Label>
                  <Form.Control
                    type="password"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="confirmPassword">
                  <Form.Label>Confirm New Password</Form.Label>
                  <Form.Control
                    type="password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                  />
                </Form.Group>
                {error && <p className="text-danger">{error}</p>}
                {success && <p className="text-success">{success}</p>}
                <Button variant="warning" type="submit">
                  Submit
                </Button>
              </Form>
            </Modal.Body>
          </Modal>

          {/* Watch Later Section */}
          <h3 className="my-4" style={{ color: '#343a40' }}>Watch Later</h3>
          <ListGroup className="mb-4">
            {watchLater.map((item, index) => (
              <ListGroup.Item key={index}>{item}</ListGroup.Item>
            ))}
          </ListGroup>

          {/* Watch History Section */}
          <h3 className="my-4" style={{ color: '#343a40' }}>Watch History</h3>
          <ListGroup>
            {watchHistory.map((item, index) => (
              <ListGroup.Item key={index}>{item}</ListGroup.Item>
            ))}
          </ListGroup>
       
        </Container>
      </div>
      
    </>
  );
};

// Inline styles for background image and container
const styles = {
  backgroundImage: {
    backgroundImage: 'url("")',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundAttachment: 'fixed',
    width: '100%',
    minHeight: '100vh',
    position: 'relative',
    zIndex: '1',
  },
  overlay: {
    position: 'absolute',
    top: '0',
    left: '0',
    width: '100%',
    height: '100%',
    backgroundColor: 'rgba(255, 255, 255, 0.3)', // Transparent overlay
    zIndex: '2',
  },
  container: {
    backgroundColor: 'rgba(255, 255, 255, 0.9)', // Semi-transparent white background for content
    borderRadius: '10px',
    padding: '20px',
    zIndex: '3',
    position: 'relative',
  },
};

export default ManageAccount;
