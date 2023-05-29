import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import Review from '../components/Review';
import NewImageDisplay from '../components/NewImageDisplay';
import { Carousel } from 'react-bootstrap';
import moment from 'moment/moment';
import { DatePicker } from 'antd';
import { Modal, Button } from 'react-bootstrap';
import ClientRegistration from '../components/ClientRegistration';
import NewReview from '../components/NewReview';

const { RangePicker } = DatePicker;

function HouseDetails() {
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState();
    const [reviews, setReviews] = useState([]);
    const [house, setHouse] = useState();
    const [isAvailable, setIsAvailable] = useState(false);
    const [fromDate, setFromdate] = useState();
    const [toDate, setTodate] = useState();
    const [nameClient, setNameClient] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');
    const [showBookNow, setShowBookNow] = useState(false);
    const handleCloseBookNow = () => setShowBookNow(false);
    const handleShowBookNow = () => setShowBookNow(true);
    const [nameUser, setNameUser] = useState('');
    const [description, setDescription] = useState('');
    const [stars, setStars] = useState('');
    const [showAddReview, setShowAddReview] = useState(false);
    const handleCloseAddReview = () => setShowAddReview(false);
    const handleShowAddReview = () => setShowAddReview(true);
    const [imageCount, setImageCount] = useState(5);

    const { houseid } = useParams();

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const responseReviews = await axios.get(`/api/v1/reviews/${houseid}`);
                const reviews = responseReviews.data;
                setReviews(reviews);
                const responseHouse = await axios.get(`/api/v1/houses/${houseid}`);
                const house = responseHouse.data;
                setHouse(house);
                setLoading(false);
            } catch (error) {
                setError(true);
                console.log('Error fetching rooms:', error);
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    useEffect(() => {
        const fetchImageCount = async () => {
            try {
                const response = await axios.get(`/api/v1/images/type/${house.name}`);
                const images = response.data;
                setImageCount(images.length);
            } catch (error) {
                console.error('Error retrieving image count:', error);
            }
        };

        if (house) {
            fetchImageCount();
        }
    }, [house]);

    const renderImages = () => {
        const images = [];
        for (let i = 1; i <= imageCount; i++) {
            images.push(
                <Carousel.Item key={i}>
                    <NewImageDisplay type={house.name} number={i} styling="custom-carousel" />
                </Carousel.Item>
            );
        }
        return images;
    };

    function checkDisponibility(dates) {
        if (dates && dates.length === 2) {
            const startDate = moment(dates[0]?.toDate()).format('YYYY-MM-DD');
            const endDate = moment(dates[1]?.toDate()).format('YYYY-MM-DD');
            setFromdate(startDate);
            setTodate(endDate);

            // Make the backend call here with the startDate and endDate
            axios.get(`/api/v1/reservations/${houseid}`)
                .then(response => {
                    const reservations = response.data;
                    // Perform availability check based on reservations data
                    const isAvailable = checkAvailability(reservations, startDate, endDate);
                    setIsAvailable(isAvailable);
                })
                .catch(error => {
                    console.log('Error fetching reservations:', error);
                    setIsAvailable(false);
                });
        } else {
            setIsAvailable(false); // Reset availability state if dates are not valid
        }
    }

    function checkAvailability(reservations, startDate, endDate) {
        for (const reservation of reservations) {
            if (
                moment(startDate).isBetween(reservation.startDate, reservation.endDate, null, '[]') ||
                moment(endDate).isBetween(reservation.startDate, reservation.endDate, null, '[]') ||
                moment(reservation.startDate).isBetween(startDate, endDate, null, '[]') ||
                moment(reservation.endDate).isBetween(startDate, endDate, null, '[]')
            ) {
                return false; // Selected dates overlap with a reservation
            }
        }
        return true; // Selected dates are available
    }

    const addNewReservation = () => {
        // Insert new client and reservation into the database
        const clientData = {
            name: nameClient,
            address: address,
            email: email,
            phoneNumber: phoneNumber,
        };
        console.log(clientData)
        axios
            .post('/api/v1/clients', clientData)
            .then(response => {
                const clientId = response.data.id;
                const reservationData = {
                    idHouse: houseid,
                    idClient: clientId,
                    startDate: fromDate,
                    endDate: toDate,
                };
                axios.post('/api/v1/reservations', reservationData).then(() => {
                    // Reservation successfully inserted
                    console.log("SUCCESS");
                    handleCloseBookNow();
                });
            })
            .catch(error => {
                console.log('Error inserting client and reservation:', error);
                alert('Insert a correct phone number and a correct email. If the email is correct and this error is still showing up, then the email is already used, so introduce another one', house.name);
            });
    };

    const alert = (message, title) => {
        window.alert(`${title ? title + '\n' : ''}${message}`);
    };

    const addNewReview = () => {
        // Check if nameUser is empty
        if (nameUser.trim() === '') {
            alert('Please enter a name.', house.name);
            return; // Stop execution if nameUser is empty
        }
        // Insert new client and reservation into the database
        const userData = {
            username: nameUser,
        };
        console.log(userData)
        axios
            .post('/api/v1/users', userData)
            .then(response => {
                const userId = response.data.id;
                const reviewData = {
                    idHouse: houseid,
                    idUser: userId,
                    description: description,
                    starsCount: stars,
                };
                axios.post('/api/v1/reviews', reviewData).then(() => {
                    // Reservation successfully inserted
                    console.log("SUCCESS");
                    handleCloseAddReview();
                    // Clear the input values
                    setNameUser('');
                    setDescription('');
                    setStars('');
                });
            })
            .catch(error => {
                console.log('Error inserting client and reservation:', error);
                alert('Name already exists. Please choose a different name.', house.name);
            });
    };

    return (
        <div>
            {loading ? (<h1>Loading...</h1>) : error ? (<h1>Error</h1>) : (
                <>
                    <div className='row justify-content-center mt-5 boxshadow m-5'>
                        <div className='col-md-6'>
                            <h1>{house.name}</h1>
                            <Carousel>{renderImages()}</Carousel>
                            <button className="btn btn-primary mt-5" onClick={handleShowAddReview}>
                                Add Review
                            </button>
                            <Modal show={showAddReview} onHide={handleCloseAddReview} size='lg'>
                                <Modal.Header>
                                    <Modal.Title>Write a review for {house.name}</Modal.Title>
                                </Modal.Header>
                                <Modal.Body>
                                    <div className='row m-5'>
                                        <NewReview name={nameUser} description={description} stars={stars}
                                            setName={setNameUser} setDescription={setDescription} setStars={setStars} />
                                    </div>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Link to={'#'}>
                                        <button className='btn btn-primary m-2' style={{ float: 'right' }} onClick={addNewReview}>Submit review</button>
                                    </Link>
                                    <Button variant="secondary" onClick={handleCloseAddReview}>
                                        Go back
                                    </Button>
                                </Modal.Footer>
                            </Modal>
                            <h3 style={{ marginTop: '1rem' }}>Reviews:<></></h3>
                        </div>
                        <div className='col-md-5'>
                            <div className='row'>
                                <div style={{ textAlign: 'right' }}>
                                    <h1>House details:</h1>
                                    <hr />
                                    <p><b>Address:</b> {house.address}</p>
                                    <p><b>Location:</b> {house.location}</p>
                                    <p><b>Price:</b> {house.price}</p>
                                </div>
                                <div>
                                    <Link to={`/bedrooms/${houseid}`}>
                                        <button className='btn btn-primary m-2' style={{ float: 'right' }} >View rooms</button>
                                    </Link>
                                    <Link to={`/bathrooms/${houseid}`}>
                                        <button className='btn btn-primary m-2' style={{ float: 'right' }} >View bathrooms</button>
                                    </Link>
                                </div>

                                <div style={{ textAlign: 'right' }}>
                                    <p className='mt-3'><b>Select a date range for booking:</b></p>
                                    <RangePicker format='YYYY-MM-DD' onChange={checkDisponibility} />
                                </div>
                                <div className='mt-3' style={{ textAlign: 'right' }}>
                                    <p><b>Availability:</b> {isAvailable ? 'Available \t' : 'Not Available \t'}
                                        <button disabled={!isAvailable} className="btn btn-primary" onClick={handleShowBookNow}>
                                            Book Now
                                        </button>
                                    </p>
                                </div>
                            </div>

                            <Modal show={showBookNow} onHide={handleCloseBookNow} size='lg'>
                                <Modal.Header>
                                    <Modal.Title>Booking details for {house.name}</Modal.Title>
                                </Modal.Header>
                                <Modal.Body>
                                    <div className='row mt-5 m-5'>
                                        <div className='col-md-6'>
                                            <NewImageDisplay type={house.name} number={1} styling={"smallimg"} /> {/* Pass the necessary props */}
                                        </div>
                                        <div className='col-md-5'>
                                            <div className='row'>
                                                <div style={{ textAlign: 'right' }}>
                                                    <hr />
                                                    <p><b>From date:</b> {fromDate}</p>
                                                    <p><b>To date:</b> {toDate}</p>
                                                    <p><b>Total days:</b> {fromDate && toDate ? moment.duration(moment(toDate).diff(moment(fromDate))).asDays() + 1 : 0}</p>
                                                    <p><b>Total amount:</b> {house.price * (fromDate && toDate ? moment.duration(moment(toDate).diff(moment(fromDate))).asDays() + 1 : 0)}</p>
                                                    <hr />
                                                </div>
                                            </div>
                                        </div>
                                        <ClientRegistration name={nameClient} email={email} phoneNumber={phoneNumber} address={address}
                                            setName={setNameClient} setEmail={setEmail} setPhoneNumber={setPhoneNumber} setAddress={setAddress} />
                                    </div>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Link to={'#'}>
                                        <button className='btn btn-primary m-2' style={{ float: 'right' }} onClick={addNewReservation}>Pay now</button>
                                    </Link>
                                    <Button variant="secondary" onClick={handleCloseBookNow}>
                                        Go back
                                    </Button>
                                </Modal.Footer>
                            </Modal>


                        </div>
                        <div className='row justify-content-center mt-5'>
                            {loading ? (<h1>Loading...</h1>) : error ? (<h1>Error</h1>) : (reviews.map(review => {
                                return <div className='col-md-9'>
                                    <Review review={review} />
                                </div>

                            }))}
                        </div>
                    </div>

                </>
            )}
        </div>
    )
}

export default HouseDetails;
