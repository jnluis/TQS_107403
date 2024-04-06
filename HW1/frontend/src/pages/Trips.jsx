import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Trip() {
    const navigate = useNavigate();

    const goBack = () => {
        navigate(-1);
      }
//   const [flights, setFlights] = useState([]);

//   useEffect(() => {
//     const fetchData = async () => {
//       // Replace with your API endpoint
//       const result = await axios('https://api.example.com/flights');
//       setFlights(result.data);
//     };

//     fetchData();
//   }, []);

const flights = [
    {
      number: '43',
      airline: 'Virgin America',
      departs: '1:43 AM',
      arrives: '9:45 PM',
      price: '$472.56'
    },
    {
      number: '234',
      airline: 'United Airlines',
      departs: '7:43 AM',
      arrives: '10:45 PM',
      price: '$432.98'
    },
    {
      number: '9696',
      airline: 'Aer Lingus',
      departs: '5:27 AM',
      arrives: '8:22 PM',
      price: '$200.98'
    },
    {
      number: '12',
      airline: 'Virgin America',
      departs: '11:23 AM',
      arrives: '1:45 PM',
      price: '$765.32'
    },
    {
      number: '4346',
      airline: 'Lufthansa',
      departs: '1:45 AM',
      arrives: '8:34 PM',
      price: '$233.98'
    }
  ];

  return (
    <div className="container ml-48  px-4">
      <h3 className="text-lg font-semibold">Flights from Paris to Buenos Aires:</h3>
      <div className='overflow-x-auto'>
      <table className="table table-fixed w-full mt-4">
        <thead>
          <tr className="text-white text-xl">
            <th>Choose</th>
            <th>Flight #</th>
            <th>Airline</th>
            <th>Departs: Paris</th>
            <th>Arrives: Buenos Aires</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight, index) => (
            <tr key={index} className="hover:bg-secondary">
              <td><button className="btn btn-primary btn-sm">Choose This Bus</button></td>
              <td>{flight.number}</td>
              <td>{flight.airline}</td>
              <td>{flight.departs}</td>
              <td>{flight.arrives}</td>
              <td>{flight.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>
      <button className="btn btn-primary mt-4" onClick={goBack}>Back</button>
      
    </div>
  );
}

export default Trip;