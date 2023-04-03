import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Client } from "@stomp/stompjs";
import "./Config.css";
import { getValue } from "@testing-library/user-event/dist/utils";

let client: Client;

function Config() {
  const navigate = useNavigate();
  const goToMaingame = () => {
    navigate("/maingame");
  };

  const [rows, setRows] = useState(0);
  const [trows, settRows] = useState(0);

  const [cols, setCols] = useState(0);
  const [tcols, settCols] = useState(0);

  const [init_plan_time, setInit_plan_time] = useState(0);
  const [tinit_plan_time, settInit_plan_time] = useState(0);

  const [init_budget, setInit_budget] = useState(0);
  const [tinit_budget, settInit_budget] = useState(0);

  const [init_dep, setInit_dep] = useState(0);
  const [tinit_dep, settInit_dep] = useState(0);

  const [init_center_dep, setInit_center_dep] = useState(0);
  const [tinit_center_dep, settInit_center_dep] = useState(0);

  const [plan_rev_time, setPlan_rev_time] = useState(0);
  const [tplan_rev_time, settPlan_rev_time] = useState(0);

  const [rev_cost, setRev_cost] = useState(0);
  const [trev_cost, settRev_cost] = useState(0);

  const [max_dep, setMax_dep] = useState(0);
  const [tmax_dep, settMax_dep] = useState(0);

  const [interest_pct, setInterest_pct] = useState(0);
  const [tinterest_pct, settInterest_pct] = useState(0);

  useEffect(() => {
    if (!client) {
      client = new Client({
        //brokerURL: "ws://localhost:8080/demo-websocket",
        brokerURL: "ws://10.172.132.237:8080/demo-websocket",
        onConnect: () => {
          client.subscribe("/app/configurationFile", (message) => {
            const body = JSON.parse(message.body);
            setRows(body["rows"]);
            setCols(body["cols"]);
            setInit_plan_time(body["init_plan_time"]);
            setInit_budget(body["init_budget"]);
            setInit_dep(body["init_dep"]);
            setInit_center_dep(body["init_center_dep"]);
            setPlan_rev_time(body["plan_rev_time"]);
            setRev_cost(body["rev_cost"]);
            setMax_dep(body["max_dep"]);
            setInterest_pct(body["interest_pct"]);
          });
          client.subscribe("/topic/configurationFile", (message) => {
            const body = JSON.parse(message.body);
            setRows(body["rows"]);
            setCols(body["cols"]);
            setInit_plan_time(body["init_plan_time"]);
            setInit_budget(body["init_budget"]);
            setInit_dep(body["init_dep"]);
            setInit_center_dep(body["init_center_dep"]);
            setPlan_rev_time(body["plan_rev_time"]);
            setRev_cost(body["rev_cost"]);
            setMax_dep(body["max_dep"]);
            setInterest_pct(body["interest_pct"]);
          });
        },
      });
      client.activate();
    }
  }, []);

  const changeConfig = () => {
    if (client) {
      if (client.connected) {
        client.publish({
          destination: "/app/changeConfig",
          body: JSON.stringify({
            rows: rows,
            cols: cols,
            init_plan_time: init_plan_time,
            init_budget: init_budget,
            init_dep: init_dep,
            init_center_dep: init_center_dep,
            plan_rev_time: plan_rev_time,
            rev_cost: rev_cost,
            max_dep: max_dep,
            interest_pct: interest_pct,
          }),
        });
      }
    }
  };

  const checkRows = () => {};

  return (
    <div className="ConfigPage">
      <div className="ConfigFrame">
        <div className="ConfigHeader">Configuration</div>
        <div className="ConfigBox">
          <div className="ConfigSubbox">
            <div className="ConfigOptionBox">
              <div className="">Rows</div>
              <input
                type="number"
                id="rows"
                className="ConfigInput"
                defaultValue={rows}
                onChange={(e) => {
                  /*let x = parseInt(e.target.value);
                  console.log("change");
                  settRows(x < 0 ? 0 : x > 20 ? 20 : x);
                  console.log(trows);
                  setRows(trows);
                  changeConfig();*/
                  //console.log(typeof x);
                }}
                /*value={trows < 0 ? 0 : trows > 20 ? 20 : trows}*/
                min={1}
                max={20}
              />
            </div>
          </div>
          <div className="ConfigSubbox">
            <div className="ConfigOptionBox">
              <div className="">Columns</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Config;
