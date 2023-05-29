import React, { useState, useEffect } from 'react';
import NewImageDisplay from './NewImageDisplay';
import { Modal, Button, Carousel } from 'react-bootstrap';
import axios from 'axios';

function Bathroom({ bathroom }) {
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [imageCount, setImageCount] = useState(3);

    useEffect(() => {
        const fetchImageCount = async () => {
            try {
                const response = await axios.get(`/api/v1/images/type/bathroom${bathroom.number}House${bathroom.idHouse}`);
                const images = response.data;
                setImageCount(images.length);
            } catch (error) {
                console.error('Error retrieving image count:', error);
            }
        };

        fetchImageCount();

    }, []);

    const renderImages = () => {
        const images = [];
        for (let i = 1; i <= imageCount; i++) {
            images.push(
                <Carousel.Item key={i}>
                    <NewImageDisplay type={`bathroom${bathroom.number}House${bathroom.idHouse}`} number={i} styling={"bigimg"} />
                </Carousel.Item>
            );
        }
        return images;
    };

    return (
        <div className="row boxshadow">
            <div className="col-md-4">
                <NewImageDisplay type={`bathroom${bathroom.number}House${bathroom.idHouse}`} number={1} styling={"smallimg"} /> {/* Pass the necessary props */}
            </div>
            <div className="col-md-7">
                <h1>Bathroom number {bathroom.number}</h1>
                <h5>Size: {bathroom.size}</h5>
                <h5>Mirrors count: {bathroom.mirrorsCount}</h5>
                <h5>Shower: {bathroom.shower ? <span>&#10004;</span> : <span>&#10006;</span>}</h5>
                <h5>Bathtub: {bathroom.bathtub ? <span>&#10004;</span> : <span>&#10006;</span>}</h5>
                <h5>WC: {bathroom.wc ? <span>&#10004;</span> : <span>&#10006;</span>}</h5>
                <h5>Sink: {bathroom.sink ? <span>&#10004;</span> : <span>&#10006;</span>}</h5>

                <div style={{ float: 'right' }}>
                    <button className="btn btn-primary" onClick={handleShow}>View details</button>
                </div>
            </div>

            <Modal show={show} onHide={handleClose} size='lg'>
                <Modal.Header>
                    <Modal.Title>Bathroom number {bathroom.number}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Carousel>{renderImages()}</Carousel>
                    <p>Details: {bathroom.details}</p>
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

export default Bathroom;
