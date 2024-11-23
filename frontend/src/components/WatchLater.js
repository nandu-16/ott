import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Row, Col, Button } from 'react-bootstrap';
import './WatchLater.css';

const WatchLater = () => {
    const [watchLaterMovies, setWatchLaterMovies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [message, setMessage] = useState('');
    const token = localStorage.getItem('token');
    const user_id = localStorage.getItem('user');

    // Fetch Watch Later movies
    useEffect(() => {
        const fetchWatchLaterMovies = async () => {
            try {
                const response = await axios.post('http://localhost:8080/Movies/getWatchLater', {
                    user_id
                });
                
                if (response.data && response.data.length > 0) {
                    // Extract the "movies" object from each item
                    const movies = response.data.map((item) => item.movies);
                    setWatchLaterMovies(movies);
                    setLoading(false);
                } else {
                    setError('No Watch Later movies added.');
                    setLoading(false);
                }
            } catch (error) {
                setError('Failed to load Watch Later movies.');
                setLoading(false);
            }
        };

        fetchWatchLaterMovies();
    }, [token, user_id]);

    // Remove a movie from Watch Later
    const handleRemoveClick = async (movieId) => {
        setMessage('');
        try {
            await axios.post(`http://localhost:8080/Movies/remove_watchLater`, {
                // headers: {
                //     Authorization: `Token ${token}`,
                //     'Content-Type': 'application/json',
                // },
               movie_id: movieId, user_id:parseInt(user_id) 
            });

            // Update the watch later movies list
            setWatchLaterMovies(watchLaterMovies.filter(movie => movie.id !== movieId));
            setMessage('Movie removed from Watch Later.');
        } catch (error) {
            console.error('Failed to remove movie:', error.message);
            setMessage('Failed to remove movie. Please try again.');
        }
    };

    if (loading) return <h1>Loading...</h1>;
    if (error) return <h1>Error: {error}</h1>;

    return (
        <div className='watch-later'>
            <h2 className="text-center my-4">Your Watch Later List</h2>
            {message && <div className={`alert ${message.includes('removed') ? 'alert-success' : 'alert-danger'}`}>{message}</div>}
            <Row>
                {watchLaterMovies.map((movie) => (
                    <Col md={4} key={movie.id} className="mb-4">
                        <div className="movie-item card p-3 shadow-sm">
                            <img src={movie.thumbnail} alt={movie.title} className="card-img-top mb-2" />
                            <div className="card-body">
                                <h5 className="card-title">{movie.title}</h5>
                                <p className="card-text">{movie.description}</p>
                                <p className="card-text"><strong>Rating:</strong> {movie.rating}</p>
                                <Button
                                    variant="danger"
                                    onClick={() => handleRemoveClick(movie.id)}
                                >
                                    Remove from Watch Later
                                </Button>
                            </div>
                        </div>
                    </Col>
                ))}
            </Row>
        </div>
    );
};

export default WatchLater;
