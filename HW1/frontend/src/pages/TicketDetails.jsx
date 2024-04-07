import { useState, useEffect } from "react";
import { useNavigate, useSearchParams } from 'react-router-dom';

import PurchaseForm from "./PurchaseForm";

function TicketDetails() {
  const navigate = useNavigate();
  const [ticket, setTicket] = useState(null);
  const [departureDay, setDepartureDay] = useState(null);
  const [departureTime, setDepartureTime] = useState(null);
  const [eurPrice, setEurPrice] = useState(null);
  const [price, setPrice] = useState(null);
  const [currencySelected, setCurrencySelected] = useState("EUR");

  const mockTicket = {
    id: "123",
    origin: "City A",
    destination: "City B",
    departureDay: "2024-04-01",
    departure_time: "08:00:00",
    price: 100,
    BusNumber: "1",
  };


  const goBack = () => {
    navigate(-1);
  }

  useEffect(() => {
    // Simulating fetching data by directly setting mock data
    setTicket(mockTicket);
    setCurrencySelected(localStorage.getItem("currency") || "EUR");
  }, []);

  // const fetchTicket = async (url) => {
  //   try {
  //     const response = await fetch(url, {
  //       method: "GET",
  //     });

  //     const responseContent = await response.json();
  //     if (response.status === 200) {
  //       console.log("Ticket found");
  //       setTicket(responseContent);
  //     } else if (response.status === 404) {
  //       console.error("Ticket not found");
  //       setTicket(null);
  //     }
  //   } catch (error) {
  //     console.error("Error:", error);
  //     setTicket(null);
  //   }
  // };

  // const fetchPrice = async () => {
  //   try {
  //     const response = await fetch(
  //       `http://localhost:8080/api/currency/price?currencyTo=${currencySelected}&amount=${eurPrice}`,
  //       {
  //         method: "GET",
  //       }
  //     );

  //     const responseContent = await response.json();
  //     if (response.status === 200) {
  //       console.log("Price found");
  //       setPrice(responseContent);
  //     } else if (response.status === 404) {
  //       console.error("Price not found");
  //       return null;
  //     }
  //   } catch (error) {
  //     console.error("Erro:", error);
  //     return null;
  //   }
  // };

  useEffect(() => {
    if (ticket) {
      setDepartureDay(ticket.departureDay);
      setDepartureTime(ticket.departure_time);
      setEurPrice(ticket.price);
      setPrice(ticket.price);
    }
  }, [ticket]);

  const submitForm = async (event) => {
    event.preventDefault();

    // const formData = new FormData(document.querySelector("form"));
    console.log("Submitting form with mock data");

    // try {
    //   const response = await fetch(
    //     `http://localhost:8080/api/reservation/reserve/${ticket.id}`,
    //     {
    //       method: "POST",
    //       headers: {
    //         "Content-Type": "application/json",
    //       },
    //       body: JSON.stringify(Object.fromEntries(formData)),
    //     }
    //   );

    //   if (response.status === 200) {
    //     console.log("Reservation created");
    //   } else if (response.status === 404) {
    //     console.error("Reservation not created");
    //   }
    // } catch (error) {
    //   console.error("Erro:", error);
    // }

    // window.location.href = "/purchaseDetails";
  };

  // useEffect(() => {
  //   const urlParts = window.location.pathname.split("/");
  //   const id = urlParts[urlParts.length - 1];

  //   const ticketUrl = `http://localhost:8080/api/tickets/${id}`;
  //   fetchTicket(ticketUrl);

  //   setCurrencySelected(localStorage.getItem("currency"));
  // }, []);

  // useEffect(() => {
  //   if (ticket) {
  //     const departureDate = new Date(ticket.departure_time);
  //     const departureTime = departureDate.toLocaleTimeString("pt-PT");
  //     const departureDay = departureDate.toLocaleDateString("pt-PT");

  //     const arrivalDate = new Date(ticket.arrival_time);
  //     const arrivalTime = arrivalDate.toLocaleTimeString("pt-PT");

  //     const ticketPrice = ticket.price;

  //     setDepartureDay(departureDay);
  //     setDepartureTime(departureTime);
  //     setArrivalTime(arrivalTime);
  //     setEurPrice(ticketPrice);
  //     setPrice(ticketPrice);

  //     // if (currencySelected !== "EUR") {
  //     //   fetchPrice();
  //     // }
  //   }
  // }, [ticket, eurPrice, currencySelected]);
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
                              {ticket.BusNumber}
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