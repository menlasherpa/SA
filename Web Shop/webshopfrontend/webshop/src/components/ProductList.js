import React, { useState, useEffect } from "react";
import { Header } from "../components/header/Header";
import { useSelector, useDispatch } from "react-redux";
import { SearchBox } from "./SearchBox";
import axios from "axios";
export const ProductList = (props) => {
  const acartListItem = {
    productNumber: "",
    quantity: 0,
    price: 0,
  };
  const dispatch = useDispatch();
  const [stockMsg, setStockMsg] = useState({});
  const productList = useSelector((state) => state.productList);
  const shoppingCart = useSelector((state) => state.shoppingCart);
  const searchKey = useSelector((state) => state.searchKey);
  useEffect(() => {
    axios.get("http://localhost:8080/products/").then((response) => {
      console.log(response.data);
      dispatch({
        type: "addproductList",
        products: response.data,
      });
    });
  }, []);
  const getQuantityFromCart = (number) => {
    let currProductFromCart = shoppingCart.cartList.filter(
      (p) => p.productNumber === number.productNumber
    );
    console.log(currProductFromCart);
    if (currProductFromCart.length == 0) {
      return 0;
    } else {
      return currProductFromCart[0].quantity;
    }
  };

  const addToCart = (e) => {
    const statusMsg = {};
    let currProduct = productList.filter(
      (p) => p.productNumber === e.target.value
    );
    if (currProduct[0].numberInStock == 0) {
      statusMsg.badMsg = "Sorry, It is not available";
    } else {
      const updateProduct = {
        productNumber: currProduct[0].productNumber,
        productName: currProduct[0].productName,
        price: currProduct[0].price,
        numberInStock: currProduct[0].numberInStock - 1,
      };

      axios
        .put("http://localhost:8080/products/" + e.target.value, updateProduct)
        .then((response) => {
          let tempCartItem = {
            productNumber: updateProduct.productNumber,
            quantity:
              getQuantityFromCart({
                productNumber: updateProduct.productNumber,
              }) + 1,
            price: updateProduct.price,
          };

          if (tempCartItem.quantity > 1) {
            dispatch({ type: "updateToCart", cartItem: tempCartItem });
          } else {
            dispatch({ type: "addToCart", cartItem: tempCartItem });
          }
          dispatch({ type: "updateTotalCost" });
          console.log("shoppingCArt" + JSON.stringify(shoppingCart));
        });
    }
    setStockMsg(statusMsg);
  };

  const viewCart = () => {
    props.history.push("/cart");
  };
  const viewProductPage = (e) => {
    props.history.push("/productpage", { productNumber: e.target.value });
  };

  return (
    <div>
      <Header />
      <button id="viewShoppingCart"onClick={viewCart}>ViewShoppingCart</button>
      <h1>Product List</h1>
      <div id="errorMsg">
        {Object.keys(stockMsg).map((msg) => (
          <p style={{ color: "red" }}>Sorry, Product is not in stock!!</p>
        ))}
      </div>

      <SearchBox />
      <table>
        <thead>
          <tr>
            <th>ProductNumber</th>
            <th>ProductName</th>
            <th>ProductPrice</th>
            <th>Number in Stock</th>
          </tr>
        </thead>
        <tbody>
          {productList
            .filter((item) => item.productName.includes(searchKey))
            .map((product) => (
              <tr class="eachProduct" key={product.productNumber}>
                <td>{product.productNumber}</td>
                <td>{product.productName}</td>
                <td>{product.price}</td>
                <td>{product.numberInStock}</td>
                <td>
                  <button
                    class="addToCart"
                    stockValue={product.numberInStock}
                    value={product.productNumber}
                    onClick={addToCart}
                  >
                    Add to cart
                  </button>
                </td>
                <td>
                  <button
                    class="productPage"
                    value={product.productNumber}
                    onClick={viewProductPage}
                  >
                    View Product Page
                  </button>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};
