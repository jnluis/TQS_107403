import React, { useState, useEffect } from 'react';
import { useNavigate, createSearchParams } from 'react-router-dom';
import axios from 'axios';

const Home = () => {
  const navigate = useNavigate();

    // States for dropdown options
    const [origins, setOrigins] = useState([]);
    const [destinations, setDestinations] = useState([]);
    const [currencies, setCurrencies] = useState([]);
    
  const [formData, setFormData] = useState({
    origin: '',
    destination: '',
    date: '',
    currency:'',
  });

  const handleSubmit = (e) => {
    e.preventDefault();
  
    // Using createSearchParams to generate query parameters
    navigate({
      pathname: '/trips',
      search: `?${createSearchParams(formData)}`,
    });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  // Fetch data for dropdowns
  useEffect(() => {
    const fetchData = async (endpoint, setState) => {
      const response = await axios.get(`http://localhost:8080/api/${endpoint}`);
      setState(response.data);
    };

    fetchData('trips/origins', setOrigins);
    fetchData('trips/destinations', setDestinations);
    fetchData('currencies/list', setCurrencies);
  }, []);
  return (
    <>
      <div className="ml-[700px] mt-[-150px] p-5 card bg-base-100 shadow-xl">
        <div className="mb-4">
          <h1 className='text-3xl text-white'>Welcome to the Bus Ticket Service </h1>
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
            {origins.map((origin) => (
              <option key={origin} value={origin}>{origin}</option>
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
            {destinations.map(destination => (
              <option key={destination} value={destination}>{destination}</option>
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
            name="currency" 
            onChange={handleChange}
            value={formData.currency}
            className="select select-bordered w-full"
          >
            <option value="">Select currency</option>
            {currencies.map(currency => (
              <option key={currency} value={currency}>{currency}</option>
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
