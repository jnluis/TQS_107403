import { useEffect, useState } from "react";
import { useNavigate,useLocation } from 'react-router-dom';

function Confirmation() {
  const navigate = useNavigate();
  const location = useLocation();
  const [ticket, setTicket] = useState(null);
  const [departureDay, setDepartureDay] = useState(null);
  const [departureTime, setDepartureTime] = useState(null);
  const [arrivalTime, setArrivalTime] = useState(null);
  const [firstName, setfirstName] = useState(null);
  const [lastName, setlastName] = useState(null);
  const [email, setEmail] = useState(null);
  const { price, currency } = location.state;

  const fetchTickets = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/ticket/list', {
        method: 'GET',
      });
      const tickets = await response.json();

      if (response.ok) {
        // Assuming the tickets array is not empty and you want the first ticket
        if (tickets.length > 0) {
          const latestTicket = tickets[0]; // Change this logic based on how you select a ticket
          setTicket(latestTicket);
          // Here you would also fetch reservation details if they're separate or set them if included in the ticket response
        }
      } else {
        console.error("Error fetching tickets:", response.statusText);
      }
    } catch (error) {
      console.error("Error fetching tickets:", error);
    }
  };

  const goFirstPage = () => {
    navigate('/');
  }

  useEffect(() => {
    fetchTickets();
  }, []);

  useEffect(() => {
    if (ticket) {
      setfirstName(ticket.firstName);
      setlastName(ticket.lastName);
      setEmail(ticket.email);
      setDepartureDay(ticket.date);
      setDepartureTime(ticket.time);
    }
  }, [ticket]);
  return (
    <div className="flex flex-col min-h-screen ml-[550px]">

      <div className="flex-1">
        {ticket && (
          <div className="max-w-lg mx-auto mt-8 p-8 rounded-md">
            <h1 className="text-4xl font-semibold text-center mb-8 text-nowrap">
              Thank you for your purchase today!
            </h1>
            <div className=" gap-4">
              <table className="table-auto mx-auto ">
                <tbody>
                  <tr>
                    <td className="px-4 py-2 font-bold">
                      Origin:
                    </td>
                    <td className=" px-4 py-2">
                      {ticket.origin}
                    </td>
                  </tr>
                  <tr>
                    <td className="px-4 py-2 font-bold">
                      Destination:
                    </td>
                    <td className="px-4 py-2">
                      {ticket.destination}
                    </td>
                  </tr>
                  <tr>
                    <td className="px-4 py-2 font-bold">
                      Date:
                    </td>
                    <td className="px-4 py-2">
                      {departureDay}
                    </td>
                  </tr>
                  <tr>
                    <td className="px-4 py-2 font-bold">
                      Time:
                    </td>
                    <td className="px-4 py-2">
                      {departureTime}
                    </td>
                  </tr>
                  <tr>
                    <td className="px-4 py-2 font-bold">
                      Bus Number:
                    </td>
                    <td className="px-4 py-2">
                      {ticket.busNumber}
                    </td>
                  </tr>
                  <tr>
                    <td className="px-4 py-2 font-bold">
                      Name:
                    </td>
                    <td className="px-4 py-2">{firstName} {lastName}</td>
                  </tr>
                  <tr>
                    <td className="px-4 py-2 font-bold">
                      Email:
                    </td>
                    <td className="px-4 py-2">{email}</td>
                  </tr>
                  <tr>
                  </tr>
                </tbody>
              </table>
              <div className="text-xl text-center mt-6">
              You payed {price} {currency}
              </div>
            </div>
            <button className="btn btn-secondary mt-4" onClick={goFirstPage}>Home</button>
          </div>
        )}
      </div>
      <div>
      </div>
    </div>
  );
}

export default Confirmation;
