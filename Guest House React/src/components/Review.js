import React, { useState, useEffect } from 'react'
import axios from 'axios';

function Review({ review }) {

    const [user, setUser] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const responseUser = await axios.get(`/api/v1/users/${review.idUser}`);
                const user = responseUser.data;
                setUser(user);
            } catch (error) {
                console.log('Error getting a user:', error);
            }
        };

        fetchData();
    }, []);

    let emoji;
    if (review.starsCount >= 5) {
        emoji = "😍";
    } else if (review.starsCount === 4 || review.starsCount === 3) {
        emoji = "😀";
    } else {
        emoji = "😐";
    }

    return (
        <div className="row boxshadow">
            <div className="col-md-7">
                <p>From user : <b>{user.username}</b></p>
                <p>Description: <em>{review.description}</em></p>
                <p>Number of stars: {review.starsCount} {emoji}</p>
            </div>
        </div>
    )
}

export default Review