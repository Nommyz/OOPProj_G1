import React, { useState } from "react";
import "./ConsPlanBox.css";

function ConsPlanBox() {
  const [isOpenOld, setIsOpenOld] = useState(false);
  const [isEditting, setIsEditting] = useState(false);

  const toggleOldWindow = () => {
    if (isOpenOld) {
      setIsOpenOld(false);
    } else {
      setIsOpenOld(true);
    }
  };
  return (
    <div style={{ display: "flex", flexDirection: "row" }}>
      {isOpenOld ? (
        <div className="ConsPlanBox old">
          <div className="Header old">
            <p>[OLD] Contruction Plan</p>
          </div>
          <textarea
            className="CodeArea old"
            name=""
            id=""
            cols={30}
            rows={10}
            spellCheck={false}
            placeholder={"Old Plan"}
            defaultValue={"Old Plan"}
            disabled={true}
          ></textarea>
        </div>
      ) : (
        <></>
      )}
      <div className="ConsPlanBox current">
        <div className="Header current">
          <div
            className={`OldBtn ${isOpenOld ? "on" : "off"}`}
            style={{ marginLeft: "8px" }}
            onClick={() => toggleOldWindow()}
          >
            <p>{isOpenOld ? "> OLD" : "< OLD"}</p>
          </div>
          <p style={{ marginLeft: "160px" }}>Construction Plan</p>
        </div>
        <textarea
          className="CodeArea current"
          name=""
          id=""
          cols={30}
          rows={10}
          spellCheck={false}
          placeholder={"Edit Your Construction Plan."}
          disabled={isEditting ? false : true}
        ></textarea>
        {isEditting ? (
          <div className="Footer">
            <div
              className="SaveBtn"
              onClick={() => {
                setIsEditting(false);
                setIsOpenOld(false);
              }}
            >
              <p>Save</p>
            </div>
            <div
              className="CancelBtn"
              onClick={() => {
                setIsEditting(false);
                setIsOpenOld(false);
              }}
              style={{ marginLeft: "20px" }}
            >
              <p>Cancel</p>
            </div>
          </div>
        ) : (
          <div className="Footer">
            <div
              className="EditBtn"
              onClick={() => {
                setIsEditting(true);
                setIsOpenOld(true);
              }}
            >
              <p>Edit</p>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
export default ConsPlanBox;
