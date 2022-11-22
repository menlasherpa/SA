import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import {
  ProductList,
  ThankYou,
  ViewShoppingCart,
  CheckoutPage,
  Payment,
  Confirmation,
  SingleProductPage
} from "./components/index";
import { ProductPage } from "./components/admin/ProductPage";
import { OrderPage } from "./components/admin/OrderPage";
import { AdminLogin } from "./components/admin/AdminLogin";
import { AdminView } from "./components/admin/AdminView";
import { Header } from "./components/header/Header";

function App() {
  return (
    <Router>
      <Route exact path="/" component={ProductList} />
      <Route path="/cart" component={ViewShoppingCart} />
      <Route path="/productpage" component={SingleProductPage}/>
      <Route path="/checkout" component={CheckoutPage} />
      <Route path="/payment" component={Payment} />
      <Route path="/confirm" component={Confirmation} />
      <Route path="/dashboard/productpage" component={ProductPage} />
      <Route path="/dashboard/order" component={OrderPage} />
      <Route path="/thankyou" component={ThankYou} />
      <Route path="/admin" component={AdminLogin} />
      <Route path="/dashboard" component={AdminView} />
    </Router>
  );
}

export default App;
