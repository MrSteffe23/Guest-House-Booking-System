import React, { useState } from 'react';
import NewImageDisplay from './NewImageDisplay';
import { Link } from 'react-router-dom';

function House({ house }) {
    return (
        <div className="row boxshadow">
            <div className="col-md-4">
                <NewImageDisplay type={house.name} number={1} styling={"smallimg"} /> {/* Pass the necessary props */}
            </div>
            <div className="col-md-7">
                <h1><b>{house.name}</b></h1>
                <h5>Address: {house.address}</h5>
                <h5>Location: {house.location}</h5>
                <h5>Price: {house.price}</h5>

                <div style={{ float: 'right' }}>
                    <Link to={`/book/${house.id}`}>
                        <button className='btn btn-primary m-2'>View Details / Book Now</button>
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default House;
