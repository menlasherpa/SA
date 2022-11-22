import React, { useState, useEffect } from "react";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
export const ProductPage = (props) => {
  const productList = useSelector((state) => state.productList);
  const dispatch = useDispatch();
  const cleanProduct = {
    productNumber: "",
    productName: "",
    price: 0.0,
    numberInStock: 0,
  };
  const [success,setSuccess]=useState("");
  const [product, setProduct] = useState(cleanProduct);
  const [productNameError, setProductNameError] = useState({});
  const [productPriceError, setProductPriceError] = useState({});
  const [productNumError, setProductNumError] = useState({});
  const [stockNumError, setStockNumError] = useState({});
  useEffect(() => {
    axios.get("http://localhost:8080/products/").then((response) => {
      console.log(response.data);
      dispatch({
        type: "addproductList",
        products: response.data,
      });
    });
  }, []);

  const handleFieldChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };
  const submitProduct = (e) => {
    e.preventDefault();
    const isValid = formValidation();
    if (isValid) {
      axios
        .post("http://localhost:8080/products/", product)
        .then((response) => {
          console.log(response.data);
        });
        setSuccess("Succesfully Added");
    }
  };

  const formValidation = () => {
    const nameErr = {};
    const priceErr = {};
    const numErr = {};
    const stockNumErr = {};

    let isValid = true;

    if (product.productNumber.trim().length < 5) {
      numErr.shortLen = " Product Number should be at least 5 characters";
      isValid = false;
    }

    if (product.productName.trim().length < 5) {
      nameErr.shortLen = " Product Name should be at least 5 numbers";
      isValid = false;
    }
    if (product.numberInStock < 0) {
      stockNumErr.shortLen = " Enter valid number!";
      isValid = false;
    }

    setProductNameError(nameErr);
    setProductPriceError(priceErr);
    setStockNumError(stockNumErr);
    setProductNumError(numErr);

    return isValid;
  };
  return (
    <div>
      <div style={{color:"green"}}>{success}</div>
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
          {productList.map((product) => (
            <tr key={product.productNumber}>
              <td>{product.productNumber}</td>
              <td>{product.productName}</td>
              <td>{product.price}</td>
              <td>{product.numberInStock}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <h1>Add Product</h1>
      <form>
        <div>
          Product Number:
          <input
            id="number"
            name="productNumber"
            type="text"
            placeholder="product number"
            value={product.productNumber}
            onChange={handleFieldChange}
          />
          {Object.keys(productNumError).map((key) => {
            return <div style={{ color: "red" }}>{productNumError[key]}</div>;
          })}
        </div>
        <div>
          ProductName:
          <input
            id="productName"
            name="productName"
            type="text"
            placeholder="Product Name"
            value={product.productName}
            onChange={handleFieldChange}
          />
          {Object.keys(productNameError).map((key) => {
            return <div style={{ color: "red" }}>{productNameError[key]}</div>;
          })}
        </div>
        <div>
          Price:
          <input
            id="price"
            name="price"
            type="text"
            value={product.price}
            onChange={handleFieldChange}
          />
          {Object.keys(productPriceError).map((key) => {
            return <div style={{ color: "red" }}>{productPriceError[key]}</div>;
          })}
        </div>

        <div>
          Number in Stock:
          <input
            id="stockNum"
            name="numberInStock"
            type="text"
            value={product.numberInStock}
            onChange={handleFieldChange}
          />
          {Object.keys(stockNumError).map((key) => {
            return <div style={{ color: "red" }}>{stockNumError[key]}</div>;
          })}
        </div>

        <button id="submitProduct" onClick={submitProduct}>
          Add Product
        </button>
      </form>
    </div>
  );
};
