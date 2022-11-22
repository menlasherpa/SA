import React from 'react';
import axios from 'axios';
import {Header} from '../components/header/Header';
import { useSelector, useDispatch } from 'react-redux';
export const Confirmation = (props) =>{
    const dispatch = useDispatch();
    const personalInfo = useSelector(state=>state.user.personalInfo);
    const paymentInfo = useSelector(state=>state.user.paymentDetails);
    const shoppingCart = useSelector(state=>state.shoppingCart);
    console.log(paymentInfo);
    console.log(personalInfo);
    const SubmitOrder = ()=>{
        axios.post("http://localhost:8080/orders/",shoppingCart ).then((response) => {
            console.log(response.data);
            dispatch({type:"resetCart"});
            props.history.push("/thankyou")
          });
          
    }
    return (
        <div>
            <Header/>
            <h1>Confirmation Details</h1>
            <div>
                <h3>Personal Details</h3>
                <div>Name:{personalInfo.name}</div>
                <div>Email:{personalInfo.email}</div>
                <div>Phone:{personalInfo.phone}</div>
                <div>Street:{personalInfo.street}</div>
                <div>City:{personalInfo.city}</div>
                <div>Zip:{personalInfo.zip}</div>
            </div>
            <div>
                <h3>Payment Details</h3>
                {/* <div>CreditCardNumber: {paymentInfo.creditNumber}</div> */}
                <div>CreditCard Type:{paymentInfo.creditcardType}</div>
                <div>ExpiryDate:{paymentInfo.validDate}</div>
                <div>CSV-Code: {paymentInfo.validationCode}</div>
            </div>
            <div>Confrim:
                <button onClick={SubmitOrder}>Confirm Order</button>
            </div>
        </div>
    );

}