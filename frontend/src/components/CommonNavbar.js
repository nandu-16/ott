import axios from 'axios';
import React from 'react';
import { Navbar, Nav, NavDropdown } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';

const CommonNavbar = () => {
  const navigate = useNavigate();

  const onLogout = async () => {
    try {
      // Retrieve token and user ID from localStorage
      const token = localStorage.getItem("token");
      const user = localStorage.getItem("user");
      console.log(token);
      console.log(user);

      if (!token || !user) {
        console.error("Token or user information missing.");
        return;
      }
      // Make the logout request
      const response = await axios.post(
        'http://localhost:8080/Logout/logout',   //logout1controllerr1function
        { user_id: user }, // Request body
        {
          headers: {
            "Content-Type": "application/json", 
            "Authorization": `Bearer ${token}`, 
          },
        }
      );



      if (response.ok) {
        // Remove token from localStorage
        localStorage.removeItem("token");
        localStorage.removeItem("user");

        // Redirect to the login page
        navigate("/login");
      } else {
        console.error("Failed to log out. Please try again.");
      }
    } catch (error) {
      console.error("An error occurred during logout:", error);
    }
  };

  return (
    <Navbar expand="lg" className="mb-4" style={{ backgroundColor: '#ffc107' }}>
      <Navbar.Brand as={Link} to="/" style={{ color: '#343a40', fontWeight: 'bold' }}>
        Bee
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="ml-auto">
          <Nav.Link as={Link} to="/" style={{ color: '#343a40', fontWeight: '500', paddingRight: '20px', transition: 'color 0.3s ease' }}
            onMouseEnter={(e) => e.target.style.color = '#fff'}
            onMouseLeave={(e) => e.target.style.color = '#343a40'}>
            Home
          </Nav.Link>
          <Nav.Link as={Link} to="/subscription" style={{ color: '#343a40', fontWeight: '500', paddingRight: '20px', transition: 'color 0.3s ease' }}
            onMouseEnter={(e) => e.target.style.color = '#fff'}
            onMouseLeave={(e) => e.target.style.color = '#343a40'}>
            Subscription
          </Nav.Link>
          <NavDropdown title="Library" id="library-dropdown" style={{ color: '#343a40', paddingRight: '20px' }}>
            <NavDropdown.Item as={Link} to="/watch-history" style={{ color: '#343a40', transition: 'background-color 0.3s ease' }}
              onMouseEnter={(e) => e.target.style.backgroundColor = '#343a40'}
              onMouseLeave={(e) => e.target.style.backgroundColor = '#fff'}>
              Watch History
            </NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/watch-later" style={{ color: '#343a40', transition: 'background-color 0.3s ease' }}
              onMouseEnter={(e) => e.target.style.backgroundColor = '#343a40'}
              onMouseLeave={(e) => e.target.style.backgroundColor = '#fff'}>
              Watch Later
            </NavDropdown.Item>
          </NavDropdown>
          <NavDropdown title="User" id="user-dropdown" style={{ color: '#343a40', paddingRight: '20px' }}>
            <NavDropdown.Item as={Link} to="/manage-subscription" style={{ color: '#343a40', transition: 'background-color 0.3s ease' }}
              onMouseEnter={(e) => e.target.style.backgroundColor = '#343a40'}
              onMouseLeave={(e) => e.target.style.backgroundColor = '#fff'}>
              Manage Subscription
            </NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/change-password" style={{ color: '#343a40', transition: 'background-color 0.3s ease' }}
              onMouseEnter={(e) => e.target.style.backgroundColor = '#343a40'}
              onMouseLeave={(e) => e.target.style.backgroundColor = '#fff'}>
              Change Password
            </NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={onLogout} style={{ color: '#dc3545', transition: 'background-color 0.3s ease' }}
              onMouseEnter={(e) => e.target.style.backgroundColor = '#dc3545'}
              onMouseLeave={(e) => e.target.style.backgroundColor = '#fff'}>
              Logout
            </NavDropdown.Item>
          </NavDropdown>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default CommonNavbar;
