import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AdminBedroom from '../components/AdminBedroom';
import { Link, useParams } from 'react-router-dom';

function AdminBedrooms() {
  const { houseId } = useParams();

  const [rooms, setRooms] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(false);

  const fetchRooms = async () => {
    try {
      setLoading(true);
      const response = await axios.get(`/api/v1/bedrooms`);
      const bedrooms = response.data;
      setRooms(bedrooms);
      setLoading(false);
    } catch (error) {
      setError(true);
      console.log('Error fetching rooms:', error);
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRooms();
  }, []);

  const handleDeleteBedroom = async (roomId) => {
    try {
      setLoading(true);
      await axios.delete(`/api/v1/bedrooms/${roomId}`);
      await fetchRooms();
    } catch (error) {
      console.error('Error deleting bedroom:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center mt-2">
        {loading ? (
          <h1>Loading...</h1>
        ) : error ? (
          <h1>Error</h1>
        ) : (
          rooms.map((room) => (
            <div className="col-md-9 mt-2" key={room.id}>
              <AdminBedroom room={room} onDelete={() => handleDeleteBedroom(room.id)} />
            </div>
          ))
        )}
      </div>
      <div>{/* Remaining code */}</div>
    </div>
  );
}

export default AdminBedrooms;
