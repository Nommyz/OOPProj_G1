import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Client } from "@stomp/stompjs";
import TerritoryBox from "../components/TerritoryBox";
import ConsPlanBox from "../components/ConsPlanBox";
import RightBox from "../components/RightBox";
import LeftBox from "../components/LeftBox";
import "./Maingame.css";

let client: Client;

function Maingame() {
  const navigate = useNavigate();
  const goToHome = () => {
    navigate("/");
  };

  return (
    <div
      className="Maingame"
      style={{
        minHeight: "100vh",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
      }}
    >
      <LeftBox />
      <div style={{ display: "flex", flexDirection: "row" }}>
        <TerritoryBox />
        <ConsPlanBox />
      </div>
      <RightBox />
    </div>
  );
}

export default Maingame;
