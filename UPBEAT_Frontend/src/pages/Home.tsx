import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Client } from "@stomp/stompjs";
import "./Home.css";

let client: Client;

function Home() {
  const navigate = useNavigate();
  const goToSelectChar = () => {
    if (btnState !== "disable") {
      //navigate("/selectchar");
      //navigate("/config");
      navigate("/maingame");
    }
  };

  const [name, setName] = useState("");
  const [btnState, setBtnState] = useState("");
  const [playersName, setPlayersName] = useState([]);
  const [numofPlayers, setNumofPlayers] = useState();
  const [isExist, setIsExist] = useState(false);
  const [isFull, setIsFull] = useState("false");

  const [budget, setBudget] = useState(0);
  const [initTime, setInitTime] = useState(0);
  const [revTime, setRevTime] = useState(0);

  useEffect(() => {
    if (isFull === "false") {
      if (name === "") {
        setBtnState("disable");
      } else {
        setBtnState("");
      }
    } else {
      setBtnState("disable");
      setIsExist(false);
    }
  }, [name, isFull]);

  useEffect(() => {
    if (!client) {
      client = new Client({
        //brokerURL: "ws://localhost:8080/demo-websocket",
        brokerURL: "ws://10.172.132.237:8080/demo-websocket",
        onConnect: () => {
          client.subscribe("/app/playersManagement", (message) => {
            const body = JSON.parse(message.body);
            setPlayersName(body["playersName"]);
            setNumofPlayers(body["numofPlayers"]);
            setIsFull(body["full"]);

            setBudget(body["budget"]);
            setInitTime(body["init_plan_time"]);
            setRevTime(body["rev_plan_time"]);
          });
          client.subscribe("/topic/playersManagement", (message) => {
            const body = JSON.parse(message.body);
            setPlayersName(body["playersName"]);
            setNumofPlayers(body["numofPlayers"]);
            setIsFull(body["full"]);

            setBudget(body["budget"]);
            setInitTime(body["init_plan_time"]);
            setRevTime(body["rev_plan_time"]);
          });
        },
      });
      client.activate();
    }
  }, []);

  const enterName = () => {
    if (client) {
      if (client.connected) {
        client.publish({
          destination: "/app/enterName",
          body: JSON.stringify({
            name: name,
          }),
        });
      }
    }
  };

  const setFromConfig = () => {
    if (client) {
      if (client.connected) {
        client.publish({
          destination: "/app/setFromConfig",
        });
      }
    }
  };

  const checkExistName = () => {
    let found = false;
    for (const playername of playersName) {
      if (playername === name) {
        found = true;
        console.log("Found this name exist.");
      }
    }
    return found;
  };

  const clickGo = () => {
    if (name !== "" && isFull === "false") {
      let found = checkExistName();
      if (found === false) {
        setIsExist(false);
        enterName();
        console.log("go");
        setFromConfig();
        goToSelectChar();
      } else {
        setIsExist(true);
      }
    }
  };

  return (
    <div className="Home">
      <div className="HomeHeader">UPBEAT</div>
      <div className="RegisterBox">
        <input
          type="text"
          name="name"
          id="name"
          onChange={(e) => {
            setName(e.target.value);
          }}
          className="NameInput"
          spellCheck={false}
          autoComplete="off"
          disabled={isFull === "true" ? true : false}
        />
        <div className={`GoButton ${btnState}`} onClick={clickGo}>
          Go!
        </div>
      </div>
      {isExist && isFull === "false" ? (
        <div className="HomeWarningText exist">
          This name has already been use.
        </div>
      ) : (
        <></>
      )}
      {isFull === "true" ? (
        <div className="HomeWarningText full">The Game is Full.</div>
      ) : (
        <></>
      )}
      <div className="HomeListBox">
        <div>{numofPlayers} Player(s) are joining!</div>
        <div className="HomeList">
          {playersName.map((item) => (
            <div>{item}</div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Home;
