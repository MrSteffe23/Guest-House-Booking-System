import React, { useState, useEffect } from 'react';
import axios from 'axios';
import NewImageDisplay from './NewImageDisplay';
import { Modal, Button, Carousel } from 'react-bootstrap';

function BedRoom({ room }) {
    const [show, setShow] = useState(false);
    const [imageCount, setImageCount] = useState(3); // Initialize with a default value

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        // Make the API request to retrieve the image count
        axios.get(`/api/v1/images/type/bedroom${room.number}House${room.idHouse}`)
          .then(response => {
            const images = response.data;
            const count = images.length;
            setImageCount(count);
          })
          .catch(error => {
            console.error('Error retrieving image count:', error);
          });
      }, [room.number]);

    const renderImages = () => {
        const images = [];
        for (let i = 1; i <= imageCount; i++) {
            images.push(
                <Carousel.Item key={i}>
                    <NewImageDisplay type={`bedroom${room.number}House${room.idHouse}`} number={i} styling={"bigimg"}/>
                </Carousel.Item>
            );
        }
        return images;
    };

    return (
        <div className="row boxshadow">
            <div className="col-md-4">
                <NewImageDisplay type={`bedroom${room.number}House${room.idHouse}`} number={1} styling={"smallimg"}/> {/* Pass the necessary props */}
            </div>
            <div className="col-md-7">
                <h1>Room number {room.number}</h1>
                <h5>Beds count: {room.bedsCount}</h5>
                <h5>Tvs count: {room.tvsCount}</h5>
                <h5>Color: {room.color}</h5>
 
                <div style={{ float: 'right' }}>
                    <button className="btn btn-primary" onClick={handleShow}>View details</button>
                </div>
            </div>

            <Modal show={show} onHide={handleClose} size='lg'>
                <Modal.Header>
                    <Modal.Title>Room number {room.number}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Carousel>{renderImages()}</Carousel>
                    <p>Details: {room.details}</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

export default BedRoom;
