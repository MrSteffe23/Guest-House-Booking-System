import React, { useState } from 'react';
import NewImageDisplay from './NewImageDisplay';
import { Modal, Button, Carousel } from 'react-bootstrap';
import ImageUploadForm from './ImageUploadForm';
import axios from 'axios';

function AdminBedRoom() {
    const [idhouse, setIdhouse] = useState();
    const [number, setNumber] = useState();
    const [bedsCount, setBedsCount] = useState();
    const [tvsCount, setTvsCount] = useState();
    const [color, setColor] = useState();
    const [details, setDetails] = useState();
    const [showModify, setShowModify] = useState(false);
    const handleCloseModify = () => setShowModify(false);
    const handleShowModify = () => setShowModify(true);
    const [deletedImages, setDeletedImages] = useState([]);
    const [modificationStatus, setModificationStatus] = useState(false); // State variable to track modification status

    const renderImagesToDelete = () => {
        return deletedImages.map((imageId, index) => {
            //console.log(imageId + " index " + index)
            return (
                <div className='m-3' key={imageId}>
                    <button onClick={() => deleteImage(imageId)}>Delete image</button>
                    <NewImageDisplay key={imageId} type={`bedroom${number}House${idhouse}`} number={index + 1} styling={"customimg"} />
                </div>
            )
        });
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
                axios.get(`/api/v1/images/type/bedroom${number}House${idhouse}`)
                    .then(response => {
                        const images = response.data;
                        const count = images.length;
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

    const handleAdd = () => {
        const updatedNumber = String(number); // Convert to string if necessary
        const updatedBedsCount = String(bedsCount); // Convert to string if necessary
        const updatedTvsCount = String(tvsCount); // Convert to string if necessary

        // Check if any field is empty
        if (
            idhouse.trim() === '' ||
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
            idHouse: idhouse,
            number: updatedNumber,
            bedsCount: updatedBedsCount,
            tvsCount: updatedTvsCount,
            color: color,
            details: details,
        };

        axios
            .post('/api/v1/bedrooms', updatedBedroom)
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
            <h1>Add a new bedroom</h1>
            <div>
                <button className='btn btn-primary' onClick={handleShowModify} style={{ width: '200px' }}>Add</button>
            </div>
            <Modal show={showModify} onHide={handleCloseModify} size='lg'>
                <Modal.Header>
                    <Modal.Title>Add a new bedroom</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div>
                        <div className='row justify-content-center mt-5'>
                            <div>
                                <div>
                                    <h3>Complete the room specifications:</h3>
                                    <div className='row justify-content-center mt-5 boxshadow m-5'>
                                        <div className="row">
                                            <label htmlFor="idhouse"><b>House Id:</b></label>
                                            <input type="text" id="idhouse" className='form-control mt-1 inputstyling' placeholder='Enter house Id'
                                                value={idhouse} onChange={(e) => { setIdhouse(e.target.value) }} />
                                        </div>
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
                                    <label><b>Images selected:</b></label>
                                    <div>
                                        {renderImagesToDelete()}
                                    </div>
                                    <label><b>Choose images to add:</b></label>
                                    <ImageUploadForm type={`bedroom${number}House${idhouse}`} onImageUpload={(imageIds) => handleImageUpload(imageIds)} />
                                </div>
                            </div>
                        </div>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseFunction}>
                        Close
                    </Button>
                    <Button variant="secondary" onClick={handleAdd}>
                        Add
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

export default AdminBedRoom;
