import React, { useEffect, useState } from 'react'
import { Tabs } from "antd"
import axios from 'axios';
import AdminBedrooms from './AdminBedrooms';
import AdminBedroomAdd from '../components/AdminBedroomAdd';

const { TabPane } = Tabs;
function AdminScreen() {
    return (
        <div className='mt-3 m-3 boxshadow'>
            <h1 className='text-center' style={{ fontSize: '30px' }}><b>Admin panel</b></h1>
            <Tabs defaultActiveKey="1" >
                <TabPane tab="Bookings" key="1">
                    <Bookings />
                </TabPane>
                <TabPane tab="Bedrooms" key="2">
                    <AdminBedrooms />
                </TabPane>
                <TabPane tab="Add bedroom" key="3">
                    <AdminBedroomAdd />
                </TabPane>
                <TabPane tab="Bathrooms" key="4">
                    <h1>Bathrooms</h1>
                </TabPane>
                <TabPane tab="Add bathroom" key="5">
                    <h1>Add Bathroom</h1>
                </TabPane>
            </Tabs>
        </div>
    )
}

export default AdminScreen

export function Bookings() {

    const [bookings, setBookings] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState();

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const responseBookings = await axios.get('/api/v1/reservations');
                const bookings = responseBookings.data;

                // Retrieve client information for each booking
                const bookingsWithClientInfo = await Promise.all(
                    bookings.map(async booking => {
                        try {
                            const clientResponse = await axios.get(`/api/v1/clients/${booking.idClient}`);
                            const clientInfo = clientResponse.data;
                            // Exclude the "id" property from the client information
                            const { id, ...clientData } = clientInfo;
                            return { ...booking, ...clientData };
                        } catch (error) {
                            console.log(`Error fetching client information for booking ${booking.id}:`, error);
                            return booking;
                        }
                    })
                );

                setBookings(bookingsWithClientInfo);
                setLoading(false);
            } catch (error) {
                setError(true);
                console.log('Error fetching bookings:', error);
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    return (
        <div className='row'>
            <div className='col-md-10'>
                <table className='table table-border table-dark'>
                    <thead className='boxshadow'>
                        <tr>
                            <th>Booking Id</th>
                            <th>House Id</th>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Client name</th>
                            <th>Client email</th>
                            <th>Client phone-number</th>
                            <th>Client address</th>
                        </tr>
                    </thead>
                    <tbody>
                        {loading ? (
                            <tr>
                                <td>Loading...</td>
                            </tr>
                        ) : error ? (
                            <tr>
                                <td>Error</td>
                            </tr>
                        ) : (
                            bookings.map(booking => (
                                <tr key={booking.id}>
                                    <td>{booking.id}</td>
                                    <td>{booking.idHouse}</td>
                                    <td>{booking.startDate}</td>
                                    <td>{booking.endDate}</td>
                                    <td>{booking.name}</td>
                                    <td>{booking.email}</td>
                                    <td>{booking.phoneNumber}</td>
                                    <td>{booking.address}</td>
                                </tr>
                            ))
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}