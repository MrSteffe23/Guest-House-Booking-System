import React, { useState, useEffect } from 'react'
import axios from 'axios';
import Bedroom from '../components/Bedroom';
import { Link, useParams } from 'react-router-dom';

function BedroomsScreen() {
    const { houseId } = useParams();

    const [rooms, setRoooms] = useState([]);
    const [loading, setLoading] = useState();
    const [error, setError] = useState();
    //console.log("Hello");
    //console.log(this.props.houseId);
    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                console.log(houseId);
                const response = await axios.get(`/api/v1/bedrooms/${houseId}`);
                const houses = response.data;
                setRoooms(houses);
                console.log(houses);

                setLoading(false);
            } catch (error) {
                setError(true);
                console.log('Error fetching rooms:', error);
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    return (
        <div className='container'>
            <div className='mt-5' style={{ display: 'flex', justifyContent: 'center'}}>
                <Link to={`../../book/${houseId}`}>
                    <button className='btn btn-primary m-2'>Go back to House details</button>
                </Link>
            </div>
            <div className='row justify-content-center mt-2'>
                {loading ? (<h1>Loading...</h1>) : error ? (<h1>Error</h1>) : (rooms.map(room => {
                    return <div className='col-md-9 mt-2'>
                        <Bedroom room={room} />
                    </div>

                }))}
            </div>
            <div>
                {/* <ImageUploadForm />
                <ImageDisplay imageId={4}/> */}
            </div>
        </div>
    )
}

export default BedroomsScreen