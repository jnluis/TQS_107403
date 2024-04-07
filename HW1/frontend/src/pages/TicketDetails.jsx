import { useState, useEffect } from "react";
import { useNavigate, useParams,useSearchParams } from 'react-router-dom';
import axios from 'axios';


import PurchaseForm from "./PurchaseForm";

function TicketDetails() {
  const navigate = useNavigate();
  const { id } = useParams(); // Extracting ID from the URL
  const [ticket, setTicket] = useState(null);
  const [departureDay, setDepartureDay] = useState(null);
  const [departureTime, setDepartureTime] = useState(null);
  const [eurPrice, setEurPrice] = useState(null);
  const [price, setPrice] = useState(null);
  const [BusNumber, setBusNumber] = useState(null);
  const [currencySelected, setCurrencySelected] = useState("EUR");

  const fetchTicketDetails = async () => {
    try {
      const intId = parseInt(id, 10); // The '10' is the radix parameter, indicating base 10 (decimal system).

      // Check if 'intId' is a valid number after conversion. If not, handle it as an error or set a default behavior.
      if (isNaN(intId)) {
        console.error("Invalid ticket ID");
        setTicket(null);
        return;
      }

      const response = await axios.get(`http://localhost:8080/api/trips/${intId}`);
      if (response.status === 200) {
        console.log("Ticket found", response.data);
        setTicket(response.data);
      } else {
        console.error("Ticket not found");
        setTicket(null);
      }
    } catch (error) {
      console.error("Error:", error);
      setTicket(null);
    }
  };

  useEffect(() => {
    fetchTicketDetails();
    setCurrencySelected(localStorage.getItem("currency") || "EUR");
  }, [id]);

  const goBack = () => {
    navigate(-1);
  }

  useEffect(() => {
    if (ticket) {
      setDepartureDay(ticket.date);
      setDepartureTime(ticket.time);
      setBusNumber(ticket.busNumber); 
      setEurPrice(ticket.price);
      setPrice(ticket.price);
    }
  }, [ticket]);

  const submitForm = async (event) => {
    event.preventDefault();

    const formData = new FormData(document.querySelector("form"));
    formData.append("tripID", id); // Append the id here

    try {
      const response = await fetch(
        `http://localhost:8080/api/ticket/reserve`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(Object.fromEntries(formData)),
        }
      );

      if (response.status === 200) {
        console.log("Reservation created");
      } else if (response.status === 404) {
        console.error("Reservation not created");
      }
    } catch (error) {
      console.error("Erro:", error);
    }

    //window.location.href = "/confirmation"; // Redirect to the purchase details page
  };

  return (
    <div className="flex flex-row ml-10">
      <div className="flex-1">
        {ticket && (
          <div>
            <div className="flex justify-center">
              <div className="grid gap-4 w-full">
                {/* Ticket Details */}
                <div className="md:col-span-1 rounded-lg p-6">
                  <div className="text-4xl font-bold text-center mb-6">
                    Your ticket from {ticket.origin} to {ticket.destination} has been reserved
                  </div>
                  <div className="flex justify-center">
                    <div className="w-full">
                      <table className="table-auto mx-auto border-collapse borde border-gray-400">
                        <tbody>
                          <tr>
                            <td className="border border-gray-400 px-4 py-2 font-bold">
                              Date
                            </td>
                            <td className="border border-gray-400 px-4 py-2">
                              {departureDay}
                            </td>
                          </tr>
                          <tr>
                            <td className="border border-gray-400 px-4 py-2 font-bold">
                              Departure Time
                            </td>
                            <td className="border border-gray-400 px-4 py-2">
                              {departureTime}
                            </td>
                          </tr>
                          <tr>
                            <td className="border border-gray-400 px-4 py-2 font-bold">
                              Bus Number
                            </td>
                            <td className="border border-gray-400 px-4 py-2">
                            {BusNumber}
                            </td>
                          </tr>
                          <tr>
                            <td className="border border-gray-400 px-4 py-2 font-bold">
                              TotalPrice
                            </td>
                            <td className="border border-gray-400 px-4 py-2">
                              {price} {currencySelected}
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <div className="text-center">Please submit the form below to purchase the flight.
                </div>
                {/* Purchase Form */}
                <div className="md:col-span-1 p-6">
                  <div className="text-4xl font-bold text-center mb-6">
                    Purchase Form
                  </div>
                  <PurchaseForm />
                </div>
              </div>
            </div>
            <div className="text-center my-10 flex justify-around">
            <button className="btn btn-secondary mt-4" onClick={goBack}>Back</button>
              <button
                className="p-6 bg-primary text-white font-bold rounded-md hover:bg-green-500 transform hover:scale-105 transition duration-300"
                onClick={submitForm}
              >
                Purchase Ticket
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default TicketDetails;