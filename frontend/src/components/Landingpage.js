import React from "react";
import {  Nav, Container, Row, Col, Button } from "react-bootstrap";
import './Landingpage.css';

function Landingpage() {
  return (
    <div className="Landingpage">
   

      {/* Hero Section */}
      <section className="hero-section text-center text-light d-flex align-items-center">
        <Container>
          <h1>Stream Unlimited Movies, TV Shows, and More</h1>
          <p className="lead">Watch anywhere, cancel anytime.</p>
          <Button className="yellow-button" size="lg" href="/login">Get Started</Button>
        </Container>
      </section>

      {/* Footer */}
      <footer className="bg-dark text-light py-3">
        <Container>
          <Row>
            <Col md={6}>
              <p>&copy; 2024 OTT Platform. All Rights Reserved.</p>
            </Col>
            <Col md={6} className="text-md-end">
              <Nav>
                <Nav.Link href="#privacy" className="text-light">Privacy Policy</Nav.Link>
                <Nav.Link href="#terms" className="text-light">Terms of Service</Nav.Link>
              </Nav>
            </Col>
          </Row>
        </Container>
      </footer>
    </div>
  );
}

export default Landingpage;

