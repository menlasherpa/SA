import React from "react";
import {Header} from '../components/header/Header';
import { useSelector } from "react-redux";
export const ThankYou = () => {
    const user = useSelector(state=>state.user.personalInfo);
  return (
    <div>
        <Header/>
      <h3 id="thankyoumsg">Hi {user.name}, Succesfully your order is confirmed!!</h3>

    </div>
  );
};
