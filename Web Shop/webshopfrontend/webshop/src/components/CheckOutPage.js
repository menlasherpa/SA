import React, { useState } from "react";
import {Header} from '../components/header/Header';
import validator from "validator";
import { useDispatch } from "react-redux";
export const CheckoutPage = (props) => {
  const cleanuserInfo = {
    name: "",
    email: "",
    phone: 0,
    street: "",
    city: "",
    zip: ""
  };
  const [newuserInfo, setNewuserInfo] = useState(cleanuserInfo);
  const [nameError, setNameError] = useState({});
  const [emailError, setEmailError] = useState({});
  const [phoneError, setPhoneError] = useState({});
  const [streetError, setStreetError] = useState({});
  const [cityError, setCityError] = useState({});
  const [zipError, setZipError] = useState({});
  const dispatch = useDispatch();

  const handleFieldChange = (e) => {
    setNewuserInfo({ ...newuserInfo, [e.target.name]: e.target.value });
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    const isValid = formValidation();
    if (isValid) {
      dispatch({ type: "addUserInfo", userinfo: newuserInfo });
      props.history.push("/payment");
    }
  };
  const formValidation = () => {
    const nameErr = {};
    const emailErr = {};
    const streetErr = {};
    const cityErr = {};
    const phoneErr = {};
    const zipErr = {};

    let isValid = true;
    if (newuserInfo.name.trim().length < 2) {
      nameErr.shortLen = " Name should be at least 2 characters";
      isValid = false;
    }
    if (!validator.isEmail(newuserInfo.email)) {
      emailErr.bigLen = " Enter valid email address!";
      isValid = false;
    }

    if (newuserInfo.phone.toString().length < 10) {
      phoneErr.shortLen = " PhoneNumber should be at least 10 numbers";
      isValid = false;
    }
    if (newuserInfo.street.trim().length < 5) {
      streetErr.shortLen = " Street should be at least 5 character";
      isValid = false;
    }
    if (newuserInfo.city.trim().length < 2) {
      cityErr.shortLen = " City should be at least 5 character";
      isValid = false;
    }
    if (newuserInfo.zip.trim().length < 5) {
      zipErr.shortLen = " Zip should be at least 5 character";
      isValid = false;
    }
    setNameError(nameErr);
    setEmailError(emailErr);
    setPhoneError(phoneErr);
    setStreetError(streetErr);
    setCityError(cityErr);
    setZipError(zipErr);
    return isValid;
  };

  return (
    <div>
        <Header/>
      <h1>Personal Information</h1>
      <form>
        <div>
          Name:
          <input
            id="name"
            name="name"
            type="text"
            placeholder="user1"
            value={newuserInfo.name}
            onChange={handleFieldChange}
          />
          {Object.keys(nameError).map((key) => {
            return <div style={{ color: "red" }}>{nameError[key]}</div>;
          })}
        </div>
        <div>
          Email:
          <input
            id="email"
            name="email"
            type="text"
            placeholder="user@gmail.com"
            value={newuserInfo.email}
            onChange={handleFieldChange}
          />
          {Object.keys(emailError).map((key) => {
            return <div style={{ color: "red" }}>{emailError[key]}</div>;
          })}
        </div>
        <div>
          Phone:
          <input
            id="phone"
            name="phone"
            type="text"
            placeholder="1234567890"
            value={newuserInfo.phone}
            onChange={handleFieldChange}
          />
          {Object.keys(phoneError).map((key) => {
            return <div style={{ color: "red" }}>{phoneError[key]}</div>;
          })}
        </div>
        <div>
          Street:
          <input
            id="street"
            name="street"
            type="text"
            placeholder="street"
            value={newuserInfo.street}
            onChange={handleFieldChange}
          />
          {Object.keys(streetError).map((key) => {
            return <div style={{ color: "red" }}>{streetError[key]}</div>;
          })}
        </div>
        <div>
          City:
          <input
            id="city"
            name="city"
            type="text"
            placeholder="city"
            value={newuserInfo.city}
            onChange={handleFieldChange}
          />
          {Object.keys(cityError).map((key) => {
            return <div style={{ color: "red" }}>{cityError[key]}</div>;
          })}
        </div>
        <div>
          Zip:
          <input
            id="zip"
            name="zip"
            type="text"
            placeholder="13455"
            value={newuserInfo.zip}
            onChange={handleFieldChange}
          />
          {Object.keys(zipError).map((key) => {
            return <div style={{ color: "red" }}>{zipError[key]}</div>;
          })}
        </div>
        <button id="personalinfo" onClick={handleSubmit}>
          Next
        </button>
      </form>
    </div>
  );
};
