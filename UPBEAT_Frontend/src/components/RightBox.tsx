import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";
import "./RightBox.css";

let client: Client;

function RightBox() {
  const [budget, setBudget] = useState(0);
  const [initTime, setInitTime] = useState(0);
  const [revTime, setRevTime] = useState(0);

  useEffect(() => {
    if (!client) {
      client = new Client({
        //brokerURL: "ws://localhost:8080/demo-websocket",
        brokerURL: "ws://10.172.132.237:8080/demo-websocket",
        onConnect: () => {
          client.subscribe("/app/playersManagement", (message) => {
            const body = JSON.parse(message.body);
            setBudget(body["budget"]);
            setInitTime(body["init_plan_time"]);
            setRevTime(body["rev_plan_time"]);
          });
          client.subscribe("/topic/playersManagement", (message) => {
            const body = JSON.parse(message.body);
            setBudget(body["budget"]);
            setInitTime(body["init_plan_time"]);
            setRevTime(body["rev_plan_time"]);
          });
        },
      });
      client.activate();
    }
  }, []);

  /*useEffect(() => {
    setFromConfig();
  }, []);

  const setFromConfig = () => {
    if (client) {
      if (client.connected) {
        client.publish({
          destination: "/app/setFromConfig",
        });
      }
    }
  };*/

  return (
    <div className="RB">
      <div className="RBCube timer">
        <div className="RBCubeSmallLetter">Timer</div>
        <div className="RBCubeBigLetter">{revTime}</div>
      </div>
      <div className="RBCube budget">
        <div className="RBCubeSmallLetter">Budget</div>
        <div className="RBCubeBigLetter">{budget}</div>
      </div>
    </div>
  );
}
export default RightBox;
