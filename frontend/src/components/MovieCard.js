import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './MovieCard.css';
import { Row, Col, Button } from 'react-bootstrap';

const MovieCard = () => {
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const token = localStorage.getItem('token');

    useEffect(() => {
        const fetchMoviesData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/Movies/GetMovies', {
                    headers: {
                        Authorization: `Token ${token}`,
                    },
                });
                setMovies(response.data.sort(() => 0.5 - Math.random()).slice(0, 5));
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        fetchMoviesData();
    }, [token]);

    if (loading) return <h1>Loading...</h1>;
    if (error) return <h1>Error: {error}</h1>;

    return (
        <div className="rowpost">
            <h2 className="text-center my-4">Recommended Movies</h2>
            <Row>
                {movies.map((movie) => (
                    <Col md={4} key={movie.id} className="mb-4">
                        <MovieItem movie={movie} />
                    </Col>
                ))}
            </Row>
        </div>
    );
};

const MovieItem = ({ movie }) => {
    const [isAdded, setIsAdded] = useState(false);
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const checkIfAdded = useCallback(async () => {
        const token = localStorage.getItem('token');
        try {
            const response = await axios.get('http://localhost:8080/Movies/WatchLater', {
                headers: { Authorization: `Token ${token}` },
            });
            setIsAdded(response.data.some(item => item.movie.id === movie.id));
        } catch (error) {
            // setMessage('Failed to load Watch Later list.');
        }
    }, [movie.id]);

    useEffect(() => {
        checkIfAdded();
    }, [checkIfAdded]);

    const handleWatchLaterClick = async () => {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('user');

        if (!token || !userId) {
            setMessage('You need to be logged in to add to Watch Later.');
            return;
        }

        try {
            await axios.post(
                'http://localhost:8080/Movies/WatchLater',
                { movie_id: movie.id, user_id: parseInt(userId, 10) },
                { headers: { Authorization: `Token ${token}`, 'Content-Type': 'application/json' } }
            );
            setMessage('Movie added to Watch Later!');
            setIsAdded(true);
        } catch (error) {
            setMessage('Failed to add movie. Please try again.');
        }
    };

    const handleWatchNowClick = async () => {
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('user');

        if (!token || !userId) {
            setMessage('You need to be logged in to watch this movie.');
            return;
        }

        try {
            await axios.post(
                'http://localhost:8080/Movies/WatchHistory',
                { movie_id: movie.id, user_id: parseInt(userId, 10) },
                { headers: { Authorization: `Token ${token}`, 'Content-Type': 'application/json' } }
            );
            navigate(`/Moviedetails/${movie.id}`);
        } catch (error) {
            setMessage('Failed to add movie to watch history. Please try again.');
        }
    };

    return (
        <div className="movie-item card p-3 shadow-sm">
            <img src={movie.thumbnail} alt={movie.title} className="card-img-top mb-2" />
            <div className="card-body">
                <h5 className="card-title">{movie.title}</h5>
                <p className="card-text">{movie.description}</p>
                <p className="card-text"><strong>Rating:</strong> {movie.rating}</p>
                {message && <div className={`alert ${message.includes('added') ? 'alert-success' : 'alert-danger'}`}>{message}</div>}
                <Button
                    variant="primary"
                    onClick={handleWatchLaterClick}
                    disabled={isAdded}
                    className="me-2"
                >
                    {isAdded ? 'Added to Watch Later' : 'Watch Later'}
                </Button>
                <Button variant="secondary" onClick={handleWatchNowClick}>
                    Watch Now
                </Button>
            </div>
        </div>
    );
};

export default MovieCard;
