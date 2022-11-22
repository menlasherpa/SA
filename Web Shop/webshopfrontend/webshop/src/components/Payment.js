import React, { useState } from "react";
import {Header} from '../components/header/Header';
import validator from "validator";
import { useDispatch } from "react-redux";

export const Payment = (props) => {
  //creditcard type, number, date valid, validation code
  const cleanpayment = {
    creditcardType: "",
    creditNumber: 0,
    validDate: "",
    validationCode: "",
  };
  const dispatch = useDispatch();
  const [paymentDetails, setPaymentDetails] = useState(cleanpayment);
  const [creditTypeError, setCreditTypeError] = useState({});
  const [creditNumberError, setCreditNumberError] = useState({});
  const [validateDateError, setValidateDateError] = useState({});
  const [validationCodeError, setValidationCodeError] = useState({});
  const handleFieldChange = (e) => {
    setPaymentDetails({ ...paymentDetails, [e.target.name]: e.target.value });
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    const isValid = formValidation();
    if (isValid) {
        console.log(paymentDetails);
      dispatch({ type: "addPaymentInfo", paymentInfo: paymentDetails });
      props.history.push("/confirm");
    }
  };

  const formValidation = () => {
    const creditcardTypeErr = {};
    const creditNumErr = {};
    const validateDateErr = {};
    const validationCodeErr = {};

    let isValid = true;
    if (paymentDetails.creditcardType.length <= 0) {
      creditTypeError.shortLen = " CreditCard Type should be selected";
      isValid = false;
    }
    if (paymentDetails.creditNumber.trim().length < 10) {
      creditNumErr.shortLen =
        " CreditCard Number should be at least 10 characters";
      isValid = false;
    }

    if (!validator.isDate(paymentDetails.validDate)) {
      validateDateErr.shortLen = " Enter valid Date!";
      isValid = false;
    }

    if (paymentDetails.validationCode.trim().length < 3) {
      validationCodeErr.shortLen =
        " validationCode Number should be at least 3 characters";
      isValid = false;
    }

    setCreditTypeError(creditcardTypeErr);
    setCreditNumberError(creditNumErr);
    setValidateDateError(validateDateErr);
    setValidationCodeError(validationCodeErr);

    return isValid;
  };

  return (
    <div>
        <Header/>
      <h1 id="payment">Payment Information</h1>
      <form>
        <div>
          Credit card type
          <input
            type="radio"
            name="creditcardType"
            value="Visa"
            checked={paymentDetails.creditcardType === "Visa"}
            onChange={handleFieldChange}
          />
          Visa
          <input
            type="radio"
            name="creditcardType"
            value="Mastercard"
            checked={paymentDetails.creditcardType === "Mastercard"}
            onChange={handleFieldChange}
          />
          Mastercard
        </div>
        <div>
          Creditcard number        
          <input
            name="creditNumber"
            type="text"
            placeholder="Credit card number"
            value={paymentDetails.creditNumber}
            onChange={handleFieldChange}
          />
          {Object.keys(creditNumberError).map((key) => {
            return <div style={{ color: "red" }}>{creditNumberError[key]}</div>;
          })}
        </div>

        <div>
          Expiry Date:
          <input
            id="validdate"
            name="validDate"
            type="date"
            placeholder="YY-MM-DD"
            value={paymentDetails.validDate}
            onChange={handleFieldChange}
          />
          {Object.keys(validateDateError).map((key) => {
            return <div style={{ color: "red" }}>{validateDateError[key]}</div>;
          })}
        </div>
        <div>
          Validation Code CSV:
          <input
            id="csvCode"
            name="validationCode"
            type="text"
            placeholder="000"
            placeholder="123"
            value={paymentDetails.validationCode}
            onChange={handleFieldChange}
          />
          {Object.keys(validationCodeError).map((key) => {
            return (
              <div style={{ color: "red" }}>{validationCodeError[key]}</div>
            );
          })}
        </div>

        <button id="paymentInfo" onClick={handleSubmit}>
          Next
        </button>
      </form>
    </div>
  );
};
