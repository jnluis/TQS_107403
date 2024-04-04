import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const cities = ['New York', 'Los Angeles', 'Chicago', 'Houston', 'Phoenix']; // Example cities

const Home = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    origin: '',
    destination: '',
    date: '',
    passengerName: '',
    passengerEmail: '',
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    // Process or send the data as needed
    navigate('/trip');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  return (
    <>
    <div className="max-w-md mx-auto my-10 p-5 card bg-base-100 shadow-xl">
      <div className="mb-4">
        <h1 className='text-2xl text-white'>Welcome to the Bus Ticket Service </h1>
        </div>
      <form onSubmit={handleSubmit} className="form-control">
        <label className="label">
          <span className="label-text">Origin</span>
        </label>
        <select
          name="origin"
          onChange={handleChange}
          value={formData.origin}
          className="select select-bordered w-full"
        >
          <option value="">Select Origin</option>
          {cities.map(city => (
            <option key={city} value={city}>{city}</option>
          ))}
        </select>

        <label className="label">
          <span className="label-text">Destination</span>
        </label>
        <select
          name="destination"
          onChange={handleChange}
          value={formData.destination}
          className="select select-bordered w-full"
        >
          <option value="">Select Destination</option>
          {cities.map(city => (
            <option key={city} value={city}>{city}</option>
          ))}
        </select>

        <label className="label">
          <span className="label-text">Date</span>
        </label>
        <input
          type="date"
          name="date"
          onChange={handleChange}
          value={formData.date}
          className="input input-bordered w-full"
        />
        <label className="label">
        <span className="label-text">Currency</span>
        </label>
        <select
          name="origin"
          onChange={handleChange}
          value={formData.origin}
          className="select select-bordered w-full"
        >
          <option value="">Select currency</option>
          {cities.map(city => (
            <option key={city} value={city}>{city}</option>
          ))}
        </select>

        <button type="submit" className="btn btn-primary mt-4">
          Search
        </button>
      </form>
    </div>
    </>
  );
};

export default Home;
