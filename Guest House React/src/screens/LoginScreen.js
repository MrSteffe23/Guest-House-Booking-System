import React, { useState } from 'react'
import axios from 'axios';

function LoginScreen() {

  const [username, setUsername] = useState([]);
  const [password, setPassword] = useState([]);

  function logIn() {
    const admin = {
      username,
      password
    }
    const fetchData = async () => {
      try {
        const responseAdmin = await axios.get(`/api/v1/admins/${username}/${password}`);
        window.location.href='./'
      } catch (error) {
        console.log('Error not a valid ADMIN:', error);
        alert('Not a valid admin account!');
      }
    };
    fetchData();
  }

  return (
    <div>
      <div className='row justify-content-center mt-5'>
        <div className='col-md-5'>
          <div className='boxshadow'>
            <h1 className='login'>Log-in</h1>
            <p>*Here you can log-in just if you are an <b>admin</b>*</p>
            <input type="text" className='form-control mt-1 inputstyling' placeholder='username'
              value={username} onChange={(e) => { setUsername(e.target.value) }} />
            <input type="password" className='form-control mt-1 inputstyling' placeholder='password' rows={4}
              value={password} onChange={(e) => { setPassword(e.target.value) }} />

            <button className='btn btn-primary mt-3' onClick={logIn}>Log-in</button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default LoginScreen