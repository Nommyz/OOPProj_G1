import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Config from "./pages/Config";
import SelectCharacter from "./pages/SelectCharacter";
import Maingame from "./pages/Maingame";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/config" element={<Config />} />
        <Route path="/selectchar" element={<SelectCharacter />} />
        <Route path="/maingame" element={<Maingame />} />
      </Routes>
    </Router>
  );
}

export default App;
