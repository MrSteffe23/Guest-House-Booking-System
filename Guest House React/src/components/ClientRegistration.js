import React, {useState, useEffect}from 'react'

function ClientRegistration({name, email, phoneNumber, address, setName, setEmail, setPhoneNumber, setAddress}) {

  return (
    <div>
        <div className='row justify-content-center mt-5'>
          <div>
            <div>
              <h3>Complete the following data:</h3>
              <input type="text" className='form-control mt-1 inputstyling' placeholder='name'
              value={name} onChange={(e) => {setName(e.target.value)}}/>
              <input type="text" className='form-control mt-1 inputstyling' placeholder='email'
              value={email} onChange={(e) => {setEmail(e.target.value)}}/>
              <input type="text" className='form-control mt-1 inputstyling' placeholder='phone-number'
              value={phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value)}}/>
              <input type="text" className='form-control mt-1 inputstyling' placeholder='address'
              value={address} onChange={(e) => {setAddress(e.target.value)}}/>
            </div>
          </div>
        </div>
    </div> 
  )
}

export default ClientRegistration