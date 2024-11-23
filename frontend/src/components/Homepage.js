import React from "react";
import { Container, Row, Col, Nav } from "react-bootstrap";
import './Homepage.css';
import CommonNavbar from "./CommonNavbar";
import MovieCard from "./MovieCard";

function Homepage() {
  return (
    <>
      <CommonNavbar />
      <MovieCard />
    </>
  );
}

export default Homepage;
