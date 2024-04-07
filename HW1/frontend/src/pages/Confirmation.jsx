import { useEffect, useState } from "react";

function Confirmation() {
  const [ticket, setTicket] = useState(null);
  const [departureDay, setDepartureDay] = useState(null);
  const [departureTime, setDepartureTime] = useState(null);
  const [arrivalTime, setArrivalTime] = useState(null);
  const [name, setName] = useState(null);
  const [email, setEmail] = useState(null);
  const [ccnumber, setCcnumber] = useState(null);

  const fetchTicket = async (url) => {
    console.log("fetching ticket");
    // try {
    //   const response = await fetch(url, {
    //     method: "GET",
    //   });

    //   const responseContent = await response.json();
    //   if (response.status === 200) {
    //     console.log("Ticket found");
    //     setTicket(responseContent);
    //   } else if (response.status === 404) {
    //     console.error("Ticket not found");
    //     setTicket(null);
    //   }
    // } catch (error) {
    //   console.error("Error:", error);
    //   setTicket(null);
    // }
  };

  const fetchReservation = async () => {
    console.log("fetching reservation");
    // try {
    //   const response = await fetch(
    //     "http://localhost:8080/api/reservation/last_id",
    //     {
    //       method: "GET",
    //     }
    //   );

    //   const responseContent = await response.json();
    //   if (response.status === 200) {
    //     console.log("Ticket found");
    //     setName(responseContent.name);
    //     setEmail(responseContent.email);
    //     setCcnumber(responseContent.cc_number);
    //     fetchTicket(
    //       `http://localhost:8080/api/tickets/${responseContent.ticketId}`
    //     );
    //   } else if (response.status === 404) {
    //     console.error("Ticket not found");
    //     setTicket(null);
    //   }
    // } catch (error) {
    //   console.error("Error:", error);
    //   setTicket(null);
    // }
  };

  useEffect(() => {
    fetchReservation();
  }, []);

  useEffect(() => {
    if (ticket) {
      const departureDate = new Date(ticket.departure_time);
      const departureTime = departureDate.toLocaleTimeString("pt-PT");
      const departureDay = departureDate.toLocaleDateString("pt-PT");

      const arrivalDate = new Date(ticket.arrival_time);
      const arrivalTime = arrivalDate.toLocaleTimeString("pt-PT");

      setDepartureDay(departureDay);
      setDepartureTime(departureTime);
      setArrivalTime(arrivalTime);
    }
  }, [ticket]);
  return (
    <div className="flex flex-col min-h-screen">

      <div className="flex-1">
        {ticket && (
          <div className="max-w-lg mx-auto mt-8 p-8 bg-white rounded-md shadow-md">
            <h1 className="text-2xl font-semibold text-center mb-4">
              Ticket Details
            </h1>
            <div className=" gap-4">
              <table className="table-auto mx-auto border-collapse border border-gray-400">
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    Origin:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">
                    {ticket.origin}
                  </td>
                </tr>
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    Destination:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">
                    {ticket.destination}
                  </td>
                </tr>
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    Departure Time:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">
                    {departureDay} at {departureTime}
                  </td>
                </tr>
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    Arrival Time:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">
                    {arrivalTime}
                  </td>
                </tr>
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    Company:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">
                    {ticket.company}
                  </td>
                </tr>
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    Name:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">{name}</td>
                </tr>
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    Email:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">{email}</td>
                </tr>
                <tr>
                  <td className="border border-gray-400 px-4 py-2 font-bold">
                    CC Number:
                  </td>
                  <td className="border border-gray-400 px-4 py-2">
                    {ccnumber}
                  </td>
                </tr>
              </table>
            </div>
          </div>
        )}
      </div>
      <div>
      </div>
    </div>
  );
}

export default Confirmation;
