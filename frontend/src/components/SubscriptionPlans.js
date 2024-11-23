// SubscriptionPlans.js
import React, { useState } from 'react';
import { Card, Button, Container, Row, Col, Pagination } from 'react-bootstrap';

const plans = [
  {
    title: "Basic Plan",
    price: "$9.99/month",
    features: ["720p HD", "1 screen", "Watch on mobile"]
  },
  {
    title: "Standard Plan",
    price: "$14.99/month",
    features: ["1080p Full HD", "2 screens", "Watch on any device"]
  },
  {
    title: "Premium Plan",
    price: "$19.99/month",
    features: ["4K Ultra HD", "4 screens", "Download to watch offline"]
  },
  {
    title: "Family Plan",
    price: "$24.99/month",
    features: ["4K Ultra HD", "5 screens", "Download to watch offline"]
  },
  {
    title: "Student Plan",
    price: "$7.99/month",
    features: ["720p HD", "1 screen", "Watch on mobile"]
  }
];

const itemsPerPage = 4;

const SubscriptionPlans = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const totalPages = Math.ceil(plans.length / itemsPerPage);
  
  // Calculate the index range for the current page
  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentPlans = plans.slice(startIndex, endIndex);

  // Handle pagination click
  const handlePaginationClick = (page) => {
    setCurrentPage(page);
  };

  return (
    <>
      <Container>
        <h2 className="text-center my-4">Choose Your Plan</h2>
        <Row>
          {currentPlans.map((plan, index) => (
            <Col md={3} key={startIndex + index}>
              <Card className="mb-4">
                <Card.Body>
                  <Card.Title>{plan.title}</Card.Title>
                  <Card.Text>
                    <strong>{plan.price}</strong>
                    <ul>
                      {plan.features.map((feature, idx) => (
                        <li key={idx}>{feature}</li>
                      ))}
                    </ul>
                  </Card.Text>
                  <Button variant="primary">Subscribe Now</Button>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
    
        {/* Pagination */}
        <Pagination className="justify-content-center">
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

        {/* My Subscription Plans Section */}
        <h2 className="text-center my-4">Current Subscription Plan</h2>
        <Card className="mb-4">
          <Card.Body>
            <p>You are currently subscribed to the following plans:</p>
            <ul>
              {/* Replace the below example with actual subscription data if available */}
              <li>Standard Plan - $14.99/month</li>
              <li>Premium Plan - $19.99/month</li>
            </ul>
          </Card.Body>
        </Card>
      </Container>
    </>
  );
};

export default SubscriptionPlans;
