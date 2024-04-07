import React, { useState, useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import axios from 'axios';

function Trip() {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [trips, setTrips] = useState([]); // State to store fetched trips

  const goBack = () => {
    navigate(-1);
  }

  useEffect(() => {
    // The advantage here is direct integration with react-router-dom's hook
    const origin = searchParams.get('origin');
    const destination = searchParams.get('destination');
    const date = searchParams.get('date');
    const currency = searchParams.get('currency');

    // Make API call to fetch trips based on query parameters
    const fetchTrips = async () => {
      const response = await axios.get(`http://localhost:8080/api/trips/list`, {
        params: {
          origin,
          destination,
          date,
          currency,
        }
      });
      setTrips(response.data);
    };

    if (origin && destination && date && currency) {
      fetchTrips();
    } else {
      console.error('Missing query parameters in URL');
    }
  }, [searchParams]); // Dependency on searchParams to re-run if they change
  return (
    <div className="container ml-48  px-4">
      <h3 className="text-lg font-semibold">Trips</h3>
      <div className='overflow-x-auto'>
        <table className="table table-fixed w-full mt-4">
          <thead>
            <tr className="text-white text-xl">
              <th>Choose</th>
              <th>Bus Number</th>
              <th>Origin</th>
              <th>Destination</th>
              <th>Date</th>
              <th>Time</th>
              <th>Price </th>
            </tr>
          </thead>
          <tbody>
            {trips.map((trip, index) => (
              <tr key={index} className="hover:bg-secondary">
                <td><button className="btn btn-primary btn-sm">Choose This Bus</button></td>
                <td>{trip.origin}</td>
                <td>{trip.busNumber}</td>
                <td>{trip.destination}</td>
                <td>{trip.date}</td>
                <td>{trip.time}</td>
                <td>{trip.price}</td>
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