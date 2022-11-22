import React, { useEffect, useState } from "react";
import axios from "axios";
import { useDispatch } from "react-redux";
export const OrderPage = (props) => {
  const [orderlist, setOrderList] = useState([]);
  const [status, setStatus] = useState({});
  useEffect(() => {
    const orders = axios
      .get("http://localhost:8080/orders/")
      .then((response) => {
        console.log("data" + JSON.stringify(response.data));
        setOrderList(response.data);
        response.data.map((curOrder) => {
          setStatus({ ...status, [curOrder.orderNumber]: curOrder.status });
        });
      });
  }, []);

  const changeStatus = (e) => {
    axios
      .put(
        "http://localhost:8080/orders/" +
          e.target.value +
          "/" +
          status[e.target.value]
      )
      .then((response) => {
        orderlist.find(order=>order.orderNumber==e.target.value).status=response.data.status;
        setOrderList(orderlist);
        console.log(orderlist);
      });
  };
  const setStatusFunc = (e) => {
    setStatus({ ...status, [e.target.name]: e.target.value });
  };
  return (
    <div>
      <h1>Orders List</h1>
      <table>
        <thead>
          <tr>
            <th>OrderNumber</th>
            <th>Order Price</th>
            <th>OrderItemsCount</th>
            <th>Status</th>
            <th>OrderDatte</th>
          </tr>
        </thead>
        <tbody>
          {orderlist.map((order) => (
            <tr key={order.orderNumber}>
              <td>{order.orderNumber}</td>
              <td>{order.orderPrice}</td>
              <td>{order.orderItemsList.length}</td>
              <td>{order.status}</td>
              <td>{order.orderDate}</td>
              <td>
                <button>View Products</button>
              </td>
              <td>
                <select
                  name={order.orderNumber}
                  value={status[order.orderNumber]}
                  onChange={setStatusFunc}
                >
                  <option>SELECT</option>
                  <option>PLACED</option>
                  <option>SHIPPED</option>
                  <option>DELIVERED</option>
                </select>
              </td>
              <button value={order.orderNumber} onClick={changeStatus}>
                Submit
              </button>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
