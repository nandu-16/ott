import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Container, Alert, Spinner } from 'react-bootstrap';
import './WatchHistory.css';

const WatchHistory = () => {
    const [history, setHistory] = useState([]);
    
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('user');

    // Fetch Watch History and Watch Later
    useEffect(() => {
        const fetchData = async () => {
            try {
                const [historyResponse] = await Promise.all([
                    axios.post('http://localhost:8080/Movies/getWatchHistory', 
                        { user_id: parseInt(userId, 10) },
                        { headers: { Authorization: `Token ${token}`, 'Content-Type': 'application/json' } }
                    ),
              
                ]);

                setHistory(historyResponse.data);
           
                setLoading(false);
            } catch (error) {
                setError('Failed to load watch history');
                setLoading(false);
            }
        };

        fetchData();
    }, [token, userId]);

    if (loading) return (
        <div className="text-center mt-5">
            <Spinner animation="border" role="status">
                <span className="visually-hidden">Loading...</span>
            </Spinner>
        </div>
    );

    if (error) return <Alert variant="danger">{error}</Alert>;

    return (
        <Container className="watch-history my-5">
            <h2 className="text-center mb-4">Your Watch History </h2>
            {history.length === 0  ? (
                <Alert variant="info">You have no watch history or movies in your Watch Later list.</Alert>
            ) : (
                <Table striped bordered hover responsive>
                    <thead className="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Watched On</th>
                            <th>Rating</th>
                        </tr>
                    </thead>
                    <tbody>
                        {history.map((movie, index) => (
                            <tr key={`history-${movie.id}`}>
                                <td>{index + 1}</td>
                                <td>{movie.title}</td>
                                <td>{movie.description}</td>
                                <td>{new Date(movie.watchedDate).toLocaleDateString()}</td>
                                <td>{movie.rating}</td>
                            </tr>
                        ))}
                       
                    </tbody>
                </Table>
            )}
        </Container>
    );
};

export default WatchHistory;
