import React from "react";
import { ProductPage } from "../SingleProductPage";
export const AdminView = (props) => {
    const viewProductPage= ()=>{
        props.history.push("/dashboard/productpage");
    }
    const viewOrderPage = ()=>{
        props.history.push("/dashboard/order");
    }
  return (
    <div>
      <h1>Welcome to Admin DashBoard</h1>
      <div id="center">
        <button onClick={viewProductPage}>View ProductList</button>
        <button onClick={viewOrderPage}>View Orders</button>
      </div>
    </div>
  );
};
