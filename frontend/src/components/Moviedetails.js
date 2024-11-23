import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Container, Row, Col, Card } from 'react-bootstrap';
import './Moviedetails.css';
import CommonNavbar from './CommonNavbar';

const Moviedetails = () => {
    const { id } = useParams();
    const movieIdInt = Number.isNaN(parseInt(id, 10)) ? null : parseInt(id, 10);
    const [movie, setMovie] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const token = localStorage.getItem('token');

    // Fetch movie details from API
    useEffect(() => {
        if (movieIdInt === null) {
            setError('Invalid movie ID.');
            setLoading(false);
            return;
        }

        const fetchMovieDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/Movies/MovieMainPage?id=${movieIdInt}`, {
                    headers: {
                        Authorization: `Token ${token}`,
                    },
                });
                setMovie(response.data);
                setLoading(false);
            } catch (error) {
                setError('Failed to load movie details.');
                setLoading(false);
            }
        };

        fetchMovieDetails();
    }, [movieIdInt, token]); // Use movieIdInt instead of id

    if (loading) return <h1>Loading...</h1>;
    if (error) return <h1>{error}</h1>;

    return (
        <>
            <CommonNavbar />
            <Container fluid className="moviedetails-page py-5">
                <Row className="mb-4">
                    <Col md={8} className="mx-auto text-center">
                        <h1 className="movie-title">{movie.title}</h1>
                    </Col>
                </Row>
                <Row className="justify-content-center">
                    <Col md={6}>
                        <Card className="movie-card mb-4">
                            <Card.Img variant="top" src={movie.thumbnail} alt={movie.title} className="movie-poster" />
                            <Card.Body>
                                <Card.Text className="movie-description">{movie.description}</Card.Text>
                                <p className="movie-rating">Rating: {movie.rating} ⭐</p>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                <Row className="justify-content-center">
                    <Col md={6}>
                        <div className="video-box">
                            <video controls width="100%" height="auto" className="movie-video">
                                <source src={movie.videoUrl} type="video/mp4" />
                                Your browser does not support the video tag.
                            </video>
                        </div>
                    </Col>
                </Row>
            </Container>
        </>
    );
};

export default Moviedetails;
