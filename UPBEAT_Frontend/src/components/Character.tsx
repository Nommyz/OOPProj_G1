import React, { useEffect, useState } from "react";
import "./Character.css";

export type CharacterProps = {
  col: number;
  row: number;
  idx: string;
  d: number;
  owned: boolean;
  isCenter: boolean;
};

function Character() {
  const [name, setName] = useState("");
  const [isSelected, setIsSelected] = useState("false");

  const toggleSelected = () => {
    if (isSelected === "true") {
      setIsSelected("false");
    } else {
      setIsSelected("true");
    }
  };

  return (
    <div className="CharacterFrame">
      <div
        className={`Character ${isSelected === "true" ? "selected" : ""}`}
        onClick={toggleSelected}
      ></div>
      {isSelected === "true" ? <div className="CharacterName">Hi</div> : <></>}
    </div>
  );
}

export default Character;
