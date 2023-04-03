import React, { useEffect, useState } from "react";
import "./Region.css";

export type RegionProps = {
  col: number;
  row: number;
  idx: string;
  d: number;
  owned: boolean;
  isCenter: boolean;
};

const Region = ({ col, row, idx, d, owned, isCenter }: RegionProps) => {
  const [mouseOver, setMouseOver] = useState(false);
  const [curState, setCurState] = useState(
    `${owned ? (isCenter ? "city-center" : "owned") : "not-owned"}`
  );
  const [curtext, setCurText] = useState(idx);

  useEffect(() => {
    if (mouseOver) {
      setCurState(
        `${
          owned
            ? isCenter
              ? "city-center-mouseover"
              : "mouseover"
            : "mouseover"
        }`
      );
      setCurText(String(d));
    } else {
      setCurState(
        `${owned ? (isCenter ? "city-center" : "owned") : "not-owned"}`
      );
      setCurText(idx);
    }
  }, [mouseOver]);

  return (
    <div
      className={`Region outer ${curState}`}
      style={{
        marginLeft: `${col * 4.5}rem`,
        marginTop: `${(row + 1) * 5 - (col % 2 === 0 ? 0 : 2.5) - 2.5}rem`,
      }}
    >
      <div
        className={`Region inner ${curState}`}
        onMouseEnter={() => setMouseOver(true)}
        onMouseLeave={() => setMouseOver(false)}
      >
        {mouseOver ? (
          <p className="Deposit">{d}</p>
        ) : (
          <p className={`Index ${curState}`}>{idx}</p>
        )}
      </div>
    </div>
  );
};

export default React.memo(Region);
