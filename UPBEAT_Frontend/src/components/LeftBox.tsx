import React from "react";
import "./LeftBox.css";

function LeftBox() {
  return (
    <div className="LB">
      <div className="LBCube">
        <div className="LBCubeTitleLetter">Enemy1</div>
        <div className="LBCubeBigLetter">10000</div>
        <div className="LBCubeSmallLetter">1800</div>
      </div>
      <div className="LBCube">
        <div className="LBCubeTitleLetter">Enemy2</div>
        <div className="LBCubeBigLetter">10000</div>
        <div className="LBCubeSmallLetter">1800</div>
      </div>
      <div className="LBCube">
        <div className="LBCubeTitleLetter">Enemy3</div>
        <div className="LBCubeBigLetter">10000</div>
        <div className="LBCubeSmallLetter">1800</div>
      </div>
    </div>
  );
}
export default LeftBox;
