import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";
import Region from "./Region";

let client: Client;

function Territory() {
  /*const grid = [
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
  ];*/

  const [territory, setTerritory] = useState([[]]);
  const [deposit, setDeposit] = useState([[]]);

  useEffect(() => {
    if (!client) {
      client = new Client({
        brokerURL: "ws://localhost:8080/demo-websocket",
        onConnect: () => {
          client.subscribe("/app/territory", (message) => {
            const body = JSON.parse(message.body);
            setTerritory(body["territory"]);
            setDeposit(body["dep"]);
          });
          client.subscribe("/topic/territory", (message) => {
            const body = JSON.parse(message.body);
            setTerritory(body["territory"]);
            setDeposit(body["dep"]);
          });
        },
      });
      client.activate();
    }
  }, []);

  return (
    <div>
      <div
        style={{
          marginLeft: "auto",
        }}
      >
        {territory.map((row, j) => (
          <div key={j}>
            {row.map((col, i) => (
              <Region
                key={`${i}${j}`}
                col={i}
                row={j}
                idx={`${j + 1},${i + 1}`}
                d={deposit[i][j]}
                owned={false}
                isCenter={false}
                //owned={i + j <= 4 ? true : false}
                //isCenter={i + j === 0 ? true : false}
              />
            ))}
          </div>
        ))}
      </div>
    </div>
  );
}

export default Territory;
