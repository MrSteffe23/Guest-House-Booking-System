import React from 'react'

function NewReview({name, description, stars, setName, setDescription, setStars}) {

  return (
    <div>
        <div className='row justify-content-center mt-5'>
          <div>
            <div>
              <h3>Complete the following data:</h3>
              <input type="text" className='form-control mt-1 inputstyling' placeholder='name'
              value={name} onChange={(e) => {setName(e.target.value)}}/>
              <textarea type="text" className='form-control mt-1 inputstyling' placeholder='description' rows={4}
              value={description} onChange={(e) => {setDescription(e.target.value)}}/>
              <input type="text" className='form-control mt-1 inputstyling' placeholder='stars (?/5)'
              value={stars} onChange={(e) => {setStars(e.target.value)}}/>
            </div>
          </div> 
        </div>
    </div> 
  )
}

export default NewReview