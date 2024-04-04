import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Trip() {
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
    <div className="container mx-auto px-4">
      <h3 className="text-lg font-semibold">Flights from Paris to Buenos Aires:</h3>
      <table className="table-fixed border-collapse border border-slate-400 w-full mt-4">
        <thead>
          <tr className="bg-slate-200">
            <th className="border border-slate-300">Choose</th>
            <th className="border border-slate-300">Flight #</th>
            <th className="border border-slate-300">Airline</th>
            <th className="border border-slate-300">Departs: Paris</th>
            <th className="border border-slate-300">Arrives: Buenos Aires</th>
            <th className="border border-slate-300">Price</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight, index) => (
            <tr key={index} className="hover:bg-slate-100">
              <td className="border border-slate-300 p-2"><button className="btn btn-primary btn-sm">Choose This Flight</button></td>
              <td className="border border-slate-300 p-2">{flight.number}</td>
              <td className="border border-slate-300 p-2">{flight.airline}</td>
              <td className="border border-slate-300 p-2">{flight.departs}</td>
              <td className="border border-slate-300 p-2">{flight.arrives}</td>
              <td className="border border-slate-300 p-2">{flight.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Trip;
