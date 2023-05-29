import React, { useState, useEffect } from 'react'
import axios from 'axios';
import House from '../components/House';

function HomeScreen( ) {

    const [houses, setHouses] = useState([]);
    const [loading, setLoading] = useState();
    const [error, setError] = useState();

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await axios.get('/api/v1/houses');
                const housesResponse = response.data;
                setHouses(housesResponse);
                console.log(housesResponse);

                setLoading(false);
            } catch (error) {
                setError(true);
                console.log('Error fetching houses:', error);
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    return (
        <div className='container'>
            <div className='row justify-content-center mt-5'>
                {loading ? (<h1>Loading...</h1>) : error ? (<h1>Error</h1>) : (houses.map(house => {
                    return  <div className='col-md-9 mt-2'>
                                <House house={house} />
                            </div>

                }))}
            </div>
        </div>
    )
}

export default HomeScreen