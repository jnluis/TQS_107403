import { BrowserRouter, Routes, Route } from "react-router-dom";
import MissingPage from "./pages/MissingPage";
import Home from "./pages/Home";
import Trip from "./pages/Trips";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />

        <Route path="/trip" element={<Trip />} />
        {/* <Route path="/dissertacoes/:id" element={<DissertationsDetails />} />
        <Route path="/perfil" element={<Profile />} /> */}

        <Route path="*" element={<MissingPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App