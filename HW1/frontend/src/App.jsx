import { BrowserRouter, Routes, Route } from "react-router-dom";
import MissingPage from "./pages/MissingPage";
import Home from "./pages/Home";
import Trip from "./pages/Trips";
import TicketDetails from "./pages/TicketDetails";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />

        <Route path="/trips" element={<Trip />} />
        <Route path="/reserve/:id" element={<TicketDetails />} />

        <Route path="*" element={<MissingPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App