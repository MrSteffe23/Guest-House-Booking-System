import React, { useState, useEffect } from 'react';
import NewImageDisplay from './NewImageDisplay';
import { Modal, Button, Carousel } from 'react-bootstrap';
import ImageUploadForm from './ImageUploadForm';
import axios from 'axios';

function AdminBedRoom({ room, onDelete }) {
    const [number, setNumber] = useState(room.number);
    const [bedsCount, setBedsCount] = useState(room.bedsCount);
    const [tvsCount, setTvsCount] = useState(room.tvsCount);
    const [color, setColor] = useState(room.color);
    const [details, setDetails] = useState(room.details);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [showModify, setShowModify] = useState(false);
    const handleCloseModify = () => setShowModify(false);
    const handleShowModify = () => setShowModify(true);
    const [imageCount, setImageCount] = useState(0); // Initialize with a default value
    const [deletedImages, setDeletedImages] = useState([]);
    const [modificationStatus, setModificationStatus] = useState(false); // State variable to track modification status


    useEffect(() => {
        axios
            .get(`/api/v1/images/type/bedroom${room.number}House${room.idHouse}`)
            .then(response => {
                const images = response.data;
                const count = images.length;
                //console.log(count);
                setImageCount(count);
                const getImageIds = async () => {
                    const imageIds = [];

                    for (let i = 1; i <= count; i++) {
                        try {
                            const imageId = await getImageIdByTypeAndNumber(i);
                            imageIds.push(imageId);
                        } catch (error) {
                            console.error('Error retrieving image ID:', error);
                        }
                    }

                    setDeletedImages(imageIds);
                };

                getImageIds();
            })
            .catch(error => {
                console.error('Error retrieving image count:', error);
            });
    }, [room.number]);

    const renderImagesToDelete = () => {
        return deletedImages.map((imageId, index) => {
            //console.log(imageId + " index " + index)
            return (
                <div className='m-3' key={imageId}>
                    <button onClick={() => deleteImage(imageId)}>Delete image</button>
                    <NewImageDisplay key={imageId} type={`bedroom${room.number}House${room.idHouse}`} number={index + 1} styling={"customimg"} />
                </div>
            )
        });
    };

    const getImageIdByTypeAndNumber = async (number) => {
        try {
            const response = await axios.get(`/api/v1/images/typeId/bedroom${room.number}House${room.idHouse}/${number}`);
            const image = response.data;
            return image.id;
        } catch (error) {
            throw error;
        }
    };

    // Function to update the deletedImages state
    const updateDeletedImages = (imageId) => {
        //console.log("Deleting inside Function image " + imageId);
        setDeletedImages((prevImages) => prevImages.filter(id => id !== imageId));

    };

    const deleteImage = (imageId) => {
        console.log("Deleting image " + imageId);
        axios
            .delete(`/api/v1/images/${imageId}`)
            .then(response => {
                // Handle successful deletion
                console.log('Image deleted successfully:', response);
                // Refresh the image count
                axios.get(`/api/v1/images/type/bedroom${room.number}House${room.idHouse}`)
                    .then(response => {
                        const images = response.data;
                        const count = images.length;
                        setImageCount(count);
                        updateDeletedImages(imageId); // Update deletedImages state after successful deletion
                    })
                    .catch(error => {
                        console.error('Error retrieving image count:', error);
                    });
            })
            .catch(error => {
                // Handle error
                console.error('Error deleting image:', imageId);
                // Display an error message to the user if needed
            });
    };

    const renderImages = () => {
        const images = [];
        for (let i = 1; i <= imageCount; i++) {
            images.push(
                <Carousel.Item key={i}>
                    <NewImageDisplay type={`bedroom${room.number}House${room.idHouse}`} number={i} styling={"bigimg"} />
                </Carousel.Item>
            );
        }
        return images;
    };

    const handleModify = () => {
        const updatedNumber = String(number); // Convert to string if necessary
        const updatedBedsCount = String(bedsCount); // Convert to string if necessary
        const updatedTvsCount = String(tvsCount); // Convert to string if necessary

        // Check if any field is empty
        if (
            updatedNumber.trim() === '' ||
            updatedBedsCount.trim() === '' ||
            updatedTvsCount.trim() === '' ||
            color.trim() === '' ||
            details.trim() === ''
        ) {
            alert('Please fill in all fields.');
            return; // Stop execution if any field is empty
        }

        const updatedBedroom = {
            idHouse: room.idHouse,
            number: updatedNumber,
            bedsCount: updatedBedsCount,
            tvsCount: updatedTvsCount,
            color: color,
            details: details,
        };

        axios
            .put(`/api/v1/bedrooms/${room.id}`, updatedBedroom)
            .then(response => {
                // Handle successful update
                console.log('Bedroom updated successfully:', response.data);
                handleCloseModify();
                // Clear the input values
                setNumber(number);
                setBedsCount(bedsCount);
                setTvsCount(tvsCount);
                setColor(color);
                setDetails(details);
                room.number = updatedNumber;
                room.bedsCount = updatedBedsCount;
                room.tvsCount = updatedTvsCount;
                room.color = color;
                room.details = details;
                setModificationStatus(!modificationStatus); // Update modification status to trigger re-render
            })
            .catch(error => {
                // Handle error
                console.error('Error updating bedroom:', error);
                // Display an error message to the user if needed
            });
    };
    
    const handleImageUpload = (imageIds) => {
        setDeletedImages((prevDeletedImages) => [...prevDeletedImages, ...imageIds]);
      };

    const handleCloseFunction = () => {
        handleCloseModify();
        setModificationStatus(!modificationStatus); // Update modification status to trigger re-render
    };

    return (
        <div className="row boxshadow">
            <div className="col-md-4">
                <NewImageDisplay type={`bedroom${room.number}House${room.idHouse}`} number={1} styling={"smallimg"}
                    key={modificationStatus ? 'modified' : 'notModified'} // Use key prop to trigger re-render
                /> {/* Pass the necessary props */}
            </div>
            <div className="col-md-7">
                <h1>Room number {room.number}</h1>
                <h5>Beds count: {room.bedsCount}</h5>
                <h5>Tvs count: {room.tvsCount}</h5>
                <h5>Color: {room.color}</h5>

                <div style={{ float: 'right' }}>
                    <div className="button-group">
                        <button className="btn btn-primary" onClick={handleShow}>View details</button>
                        <button className="btn btn-primary" onClick={handleShowModify}>Modify details</button>
                        <button className="btn btn-primary" onClick={onDelete}>Delete Bedroom</button>
                    </div>
                </div>
            </div>

            <Modal show={show} onHide={handleClose} size='lg' key={modificationStatus ? 'modified' : 'notModified'}>
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

            <Modal show={showModify} onHide={handleCloseModify} size='lg'>
                <Modal.Header>
                    <Modal.Title>Modify Room number {room.number}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <div className='row justify-content-center mt-5'>
                            <div>
                                <div>
                                    <h3>Complete the room specifications:</h3>
                                    <div className='row justify-content-center mt-5 boxshadow m-5'>
                                        <div className="row">
                                            <label htmlFor="number"><b>Number:</b></label>
                                            <input type="text" id="number" className='form-control mt-1 inputstyling' placeholder='Enter number'
                                                value={number} onChange={(e) => { setNumber(e.target.value) }} />
                                        </div>
                                        <div className="row">
                                            <label htmlFor="bedsCount"><b>Beds Count:</b></label>
                                            <input type="text" id="bedsCount" className='form-control mt-1 inputstyling' placeholder='Enter beds count'
                                                value={bedsCount} onChange={(e) => { setBedsCount(e.target.value) }} />
                                        </div>
                                        <div className="row">
                                            <label htmlFor="tvsCount"><b>TVs Count:</b></label>
                                            <input type="text" id="tvsCount" className='form-control mt-1 inputstyling' placeholder='Enter TVs count'
                                                value={tvsCount} onChange={(e) => { setTvsCount(e.target.value) }} />
                                        </div>
                                        <div className="row">
                                            <label htmlFor="color"><b>Color:</b></label>
                                            <input type="text" id="color" className='form-control mt-1 inputstyling' placeholder='Select color'
                                                value={color} onChange={(e) => { setColor(e.target.value) }} />
                                        </div>
                                        <div className="row">
                                            <label htmlFor="details"><b>Details:</b></label>
                                            <textarea type="text" id="details" className='form-control mt-1 inputstyling' placeholder='Enter details' rows={3}
                                                value={details} onChange={(e) => { setDetails(e.target.value) }} />
                                        </div>
                                    </div>
                                    <label><b>Choose images to delete:</b></label>
                                    <div>
                                        {renderImagesToDelete()}
                                    </div>
                                    <label><b>Choose images to add:</b></label>
                                    <ImageUploadForm type={`bedroom${room.number}House${room.idHouse}`} onImageUpload={(imageIds) => handleImageUpload(imageIds)} />
                                </div>
                            </div>
                        </div>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseFunction}>
                        Close
                    </Button>
                    <Button variant="secondary" onClick={handleModify}>
                        Update
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

export default AdminBedRoom;
