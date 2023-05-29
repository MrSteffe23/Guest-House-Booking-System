import React, { useState } from 'react';
import axios from 'axios';

function ImageUploadForm({ type, onImageUpload }) {
  const [file, setFile] = useState(null);
  const [uploadSuccess, setUploadSuccess] = useState(false);

  const handleFileChange = (event) => {
    setFile(event.target.files);
    setUploadSuccess(false); // Clear upload success message when new files are selected
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    if (!file || file.length === 0) {
      console.error('No files selected');
      return;
    }
  
    try {
      const uploadedImageIds = [];
      
      for (let i = 0; i < file.length; i++) {
        const formData = new FormData();
        formData.append('image', file[i]);
  
        const response = await axios.post(`/api/v1/images/${type}`, formData);
        const imageId = response.data.id;
        //console.log(imageId);
        uploadedImageIds.push(imageId);
        console.log(`Image ${i + 1} uploaded successfully`);
  
        // Do something on successful upload
      }
      
      setUploadSuccess(true); // Set upload success message to true after successful upload
      setFile(null); // Clear selected files
      
      // Call the onImageUpload callback with the uploaded image IDs
      if (typeof onImageUpload === 'function') {
        //console.log("din upload: " + uploadedImageIds)
        onImageUpload(uploadedImageIds);
      }
      
    } catch (error) {
      console.error('Error uploading images:', error);
      // Handle upload error
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="file" accept="image/*" multiple onChange={handleFileChange} />
      <button type="submit">Upload Images</button>
      {uploadSuccess && <p>PHOTOS ADDED SUCCESSFULLY</p>}
    </form>
  );
}

export default ImageUploadForm;
