import React, { useState } from "react";
import { Header } from "./header/Header";

import { useSelector, useDispatch } from "react-redux";
export const ViewShoppingCart = (props) => {
  const dispatch = useDispatch();
  const shoppingCart = useSelector((state) => state.shoppingCart);
  const removeFromCart = (e) => {
    
    dispatch({ type: "removeFromCart", productNumber: e.target.value });
    dispatch({ type: "updateTotalCost" });
  };
  const handleFieldChange = (e) => {
    
    let curProduct = shoppingCart.cartList.filter(
      (p) => p.productNumber === e.target.id
    );
    curProduct[0].quantity = e.target.value;
    dispatch({ type: "updateToCart", cartItem: curProduct[0] });
    dispatch({ type: "updateTotalCost" });
  };
  const checkOutPage = () => {
    props.history.push("/checkout");
  };
  return (
    <div>
        <Header/>
      <button onClick={checkOutPage}>Checkout</button>
      <h1> View CartItems</h1>
      <table>
        <thead>
          <tr>
            <th>ProductNumber</th>
            <th>Quanitity</th>
            <th>PricePerItem</th>
          </tr>
        </thead>
        <tbody>
          {shoppingCart.cartList.map((cart) => (
            <tr class="eachCartProduct" key={cart.productNumber}>
              <td>{cart.productNumber}</td>
              <td>
                <input
                  id={cart.productNumber}
                  type="number"
                  name="quantity"
                  value={cart.quantity}
                  onChange={handleFieldChange}
                />
              </td>
              <td>{cart.price}</td>
              <td>
                <button id="removeFromCart" value={cart.productNumber} onClick={removeFromCart}>
                  Remove from Cart
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
