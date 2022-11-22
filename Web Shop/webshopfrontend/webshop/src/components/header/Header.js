import React from "react";
import "./Header.css";
import { useSelector } from "react-redux";
export const Header = (props) => {
  const shoppingCart = useSelector((state) => state.shoppingCart);
  
  return (
    <div id="header">
      <h1 id="headerMSG">Welcome to MIU WEBSHOP</h1>
      <div id="right">
        <p>shopping cart Items:<span id="cartValue">{shoppingCart.cartList.length}</span></p>
        <p>totalCharge:{shoppingCart.totalMoney}</p>
      </div>
    </div>
  );
};

