import React, { useState } from 'react';
import { Card, Button, Container, Row, Col, Pagination } from 'react-bootstrap';
import CommonNavbar from './CommonNavbar';

// Current subscription plan details
const currentPlan = {
  title: "Standard Plan",
  price: "$14.99/month",
  features: ["1080p Full HD", "2 screens", "Watch on any device"]
};

// Sample payment history data
const paymentHistory = [
  { id: 1, date: '2024-01-01', amount: '$14.99', details: 'Payment for January 2024' },
  { id: 2, date: '2024-02-01', amount: '$14.99', details: 'Payment for February 2024' },
  { id: 3, date: '2024-03-01', amount: '$14.99', details: 'Payment for March 2024' },
  { id: 4, date: '2024-04-01', amount: '$14.99', details: 'Payment for April 2024' },
  { id: 5, date: '2024-05-01', amount: '$14.99', details: 'Payment for May 2024' },
  { id: 6, date: '2024-06-01', amount: '$14.99', details: 'Payment for June 2024' }
];

const itemsPerPage = 3; // Number of payments to show per page

const SubscriptionManagement = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const totalPages = Math.ceil(paymentHistory.length / itemsPerPage);

  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentPayments = paymentHistory.slice(startIndex, endIndex);

  const handlePaginationClick = (page) => {
    setCurrentPage(page);
  };

  return (
    <>
    <CommonNavbar/>
    <Container className="my-5" style={{ backgroundColor: '#f8f9fa', padding: '20px', borderRadius: '5px' }}>
      <h2 className="text-center my-4" style={{ color: '#ffc107', textTransform: 'uppercase' }}>Current Plan</h2>
      <Row className="justify-content-center">
        <Col md={6}>
          <Card className="mb-4 shadow-sm" style={{ border: 'none' }}>
            <Card.Body>
              <Card.Title className="text-center" style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>{currentPlan.title}</Card.Title>
              <Card.Text className="text-center" style={{ fontSize: '1.2rem' }}>
                <strong>{currentPlan.price}</strong>
                <ul style={{ listStyleType: 'none', padding: '0' }}>
                  {currentPlan.features.map((feature, idx) => (
                    <li key={idx} style={{ margin: '5px 0' }}>{feature}</li>
                  ))}
                </ul>
              </Card.Text>
              <div className="text-center">
                <Button variant="primary" style={{ backgroundColor: '#007bff', borderColor: '#007bff' }}>Manage Plan</Button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      <h2 className="text-center my-4" style={{ color: '#343a40', textTransform: 'uppercase' }}>Payment History</h2>
      <Row>
        {currentPayments.map((payment) => (
          <Col md={4} key={payment.id}>
            <Card className="mb-4 shadow-sm" style={{ border: 'none' }}>
              <Card.Body>
                <Card.Title>Payment ID: {payment.id}</Card.Title>
                <Card.Text>
                  <strong>Date:</strong> {payment.date}<br />
                  <strong>Amount:</strong> {payment.amount}
                </Card.Text>
                <Button variant="info" onClick={() => alert(payment.details)}>View Details</Button>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      {/* Pagination for payment history */}
      <Pagination className="justify-content-center mt-4">
        {Array.from({ length: totalPages }, (_, index) => (
          <Pagination.Item
            key={index + 1}
            active={index + 1 === currentPage}
            onClick={() => handlePaginationClick(index + 1)}
          >
            {index + 1}
          </Pagination.Item>
        ))}
      </Pagination>
    </Container>
    </>
  );
};

export default SubscriptionManagement;
