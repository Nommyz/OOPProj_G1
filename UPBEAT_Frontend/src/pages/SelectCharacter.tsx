import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Client } from "@stomp/stompjs";
import "./SelectCharacter.css";
import Character from "../components/Character";

let client: Client;

function PlayerSelect() {
  const navigate = useNavigate();
  const goToMaingame = () => {
    navigate("/maingame");
  };

  const [char1, setChar1] = useState("");
  const [isChar1Selected, setIsChar1Selected] = useState();

  const [char2, setChar2] = useState("");
  const [isChar2Selected, setIsChar2Selected] = useState();

  const [char3, setChar3] = useState("");
  const [isChar3Selected, setIsChar3Selected] = useState();

  const [char4, setChar4] = useState("");
  const [isChar4Selected, setIsChar4Selected] = useState();

  useEffect(() => {
    if (!client) {
      client = new Client({
        brokerURL: "ws://localhost:8080/demo-websocket",
        onConnect: () => {
          client.subscribe("/app/charSelect", (message) => {
            const body = JSON.parse(message.body);
          });
          client.subscribe("/topic/charSelect", (message) => {
            const body = JSON.parse(message.body);
          });
        },
      });
      client.activate();
    }
  }, []);

  return (
    <div className="PlayerSelectPage">
      <p className="HeaderFont">Select a Character</p>
      <div className="BigContainer">
        <div className="CharactersContainer">
          <Character />
          <Character />
          <Character />
          <Character />
        </div>
        <div className="ConfigContainer">
          <p className="HeaderFont">Config Game Settings</p>
        </div>
      </div>
      <p onClick={goToMaingame}>PLAY</p>
    </div>
  );
}

export default PlayerSelect;
