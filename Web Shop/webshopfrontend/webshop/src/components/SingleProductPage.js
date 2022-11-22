import React from "react";
import { Header } from "./header/Header";
import { useDispatch, useSelector } from "react-redux";

export const SingleProductPage = (props) => {
  const productList = useSelector((state) => state.productList);
  const shoppingCart = useSelector((state) => state.shoppingCart);
  let aProduct = productList.filter(
    (p) => p.productNumber === props.location.state.productNumber
  );
  return (
    <div>
      <Header />
      <h1>ProductPage</h1>

      <table>
        <thead>
          <tr>
            <th>Product Number</th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Product Number in Stock</th>
          </tr>
        </thead>
        <tbody>
          <tr key={aProduct[0].productNumber}>
            <td id="productNumber">{aProduct[0].productNumber}</td>
            <td>{aProduct[0].productName}</td>
            <td>{aProduct[0].price}</td>
            <td>{aProduct[0].numberInStock}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};
