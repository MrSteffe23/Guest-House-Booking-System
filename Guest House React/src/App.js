import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import Navbar from './components/Navbar';
import { BrowserRouter, Route, Routes, Link } from 'react-router-dom';
import BedroomsScreen from './screens/BedroomsScreen';
import HomeScreen from './screens/HomeScreen';
import HouseDetails from './screens/HouseDetails';
import BathroomsScreen from './screens/BathroomsScreen';
import LoginScreen from './screens/LoginScreen';
import AdminScreen from './screens/AdminScreen';

function App() {
  return (
    <div className="App">
      <Navbar />
      <BrowserRouter>
        <Routes>
          <Route exact path="/bedrooms/:houseId" element={<BedroomsScreen />} />
          <Route exact path="/bathrooms/:houseId" element={<BathroomsScreen />} />
          <Route exact path="/" element ={<HomeScreen/>} />
          <Route exact path="/book/:houseid" element={<HouseDetails />} />
          <Route exact path="/admin/login" element ={<LoginScreen/>} />
          <Route exact path="/admin" element ={<AdminScreen/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
