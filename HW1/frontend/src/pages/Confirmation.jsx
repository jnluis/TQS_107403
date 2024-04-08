import { useEffect, useState } from "react";

function Confirmation() {
  const [ticket, setTicket] = useState(null);
  const [departureDay, setDepartureDay] = useState(null);
  const [departureTime, setDepartureTime] = useState(null);
  const [arrivalTime, setArrivalTime] = useState(null);
  const [firstName, setfirstName] = useState(null);
  const [lastName, setlastName] = useState(null);
  const [email, setEmail] = useState(null);
  const [ccnumber, setCcnumber] = useState(null);

    // Mock data to simulate API response
    const mockTicket = {
      origin: "New York",
      destination: "Los Angeles",
      date: "2024-04-15",
      time: "17:30",
      busNumber: "15",
    };
  
    const mockReservation = {
      firstName: "John",
      lastName: "Doe",
      email: "john.doe@example.com",
      cc_number: "1234-5678-9012-3456",
      ticketId: mockTicket.ticketId,
    };

  const fetchTicket = async (url) => {
    console.log("fetching ticket");
    setTicket(mockTicket);
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
    setfirstName(mockReservation.firstName);
    setlastName(mockReservation.lastName);
    setEmail(mockReservation.email);
    setCcnumber(mockReservation.cc_number);
    fetchTicket();
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

      setDepartureDay(ticket.date);
      setDepartureTime(ticket.time);
      setArrivalTime(arrivalTime);
    }
  }, [ticket]);
  return (
    <div className="flex flex-col min-h-screen">

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
                    Arrival Time:
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
