import React, { useEffect, useState } from 'react';
import axios from 'axios';

const NewImageDisplay = ({ type, number, styling}) => {
  const [imageData, setImageData] = useState(null);

  useEffect(() => {
    axios
      .get(`/api/v1/images/type/${type}/${number}`, { responseType: 'arraybuffer' })
      .then((response) => {
        const imageBlob = new Blob([response.data], { type: 'image/jpeg' }); // Adjust the image type if needed
        const imageUrl = URL.createObjectURL(imageBlob);
        setImageData(imageUrl);
      })
      .catch((error) => {
        console.error('Error retrieving image:', error);
        // Handle image retrieval error
      });

    // Clean up the URL.createObjectURL when component unmounts
    return () => {
      if (imageData) {
        URL.revokeObjectURL(imageData);
      }
    };
  }, [type, number]);

  if (!imageData) {
    return <div>No image yet</div>;
  }

  return (
    <img src={imageData} alt="Image" className={styling} />
  );
};

export default NewImageDisplay;
