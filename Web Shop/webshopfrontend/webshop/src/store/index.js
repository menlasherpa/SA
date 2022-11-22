import { createStore } from "redux";

const initialState = {
  admin: {
    username: "admin",
    password: "1234567",
  },
  user: {
    personalInfo: {},
    paymentDetails: {},
  },
  searchKey: "",
  shoppingCart: {
    cartList: [],
    totalMoney: 0.0,
  },
  productList: [],
};
export const webshopReducer = (state = initialState, action) => {
  if (action.type === "addproductList") {
    return {
      admin: state.admin,
      user: state.user,
      searchKey: state.searchKey,
      productList: [...action.products],
      shoppingCart: { ...state.shoppingCart },
    };
  }
  if (action.type === "updateTotalCost") {
    let totalPrice = 0.0;
    {
      state.shoppingCart.cartList.map((cart) => {
        let price = parseFloat(cart.price);
        let quantity = parseFloat(cart.quantity);
        console.log(price + "-Price, " + quantity + "quanitity");
        totalPrice += price * quantity;
      });
    }
    return {
      admin: state.admin,
      user: state.user,
      searchKey: state.searchKey,
      productList: state.productList,
      shoppingCart: {
        ...state.shoppingCart,
        cartList: state.shoppingCart.cartList,
        totalMoney: totalPrice,
      },
    };
  }

  if (action.type === "addToCart") {
    return {
      admin: state.admin,
      user: state.user,
      searchKey: state.searchKey,
      shoppingCart: {
        cartList: state.shoppingCart.cartList.concat(action.cartItem),
        totalMoney: state.shoppingCart.totalMoney,
      },
      productList: state.productList,
    };
  }
  if (action.type === "addToCart") {
    return {
      admin: state.admin,
      user: state.user,
      searchKey: state.searchKey,
      shoppingCart: {
        cartList: state.shoppingCart.cartList.concat(action.cartItem),
        totalMoney: state.shoppingCart.totalMoney,
      },
      productList: state.productList,
    };
  }
  if (action.type === "updateToCart") {
    const index = state.shoppingCart.cartList.findIndex(
      ({ productNumber }) => productNumber === action.cartItem.productNumber
    );
    const newCartList = [...state.shoppingCart.cartList];
    newCartList[index] = action.cartItem;

    return {
      admin: state.admin,
      user: state.user,
      searchKey: state.searchKey,
      shoppingCart: {
        cartList: newCartList,
        totalMoney: state.shoppingCart.totalMoney,
      },
      productList: state.productList,
    };
  }
  if (action.type === "removeFromCart") {
    let newproductList = state.shoppingCart.cartList.filter(
      (p) => p.productNumber != action.productNumber
    );
    return {
      admin: state.admin,
      user: state.user,
      searchKey: action.searchKey,
      shoppingCart: {
        cartList: newproductList,
        totalMoney: state.shoppingCart.totalMoney,
      },
      productList: state.productList,
    };
  }
  if (action.type === "addUserInfo") {
    return {
      admin: state.admin,
      user: {
        personalInfo: action.userinfo,
        paymentDetails: state.user.paymentDetails,
      },
      searchKey: state.searchKey,
      productList: state.productList,
      shoppingCart: state.shoppingCart,
    };
  }
  if (action.type === "setSearchKey") {
    return {
      admin: state.admin,
      user: state.user,
      searchKey: action.searchKey,
      shoppingCart: {
        cartList: state.shoppingCart.cartList,
        totalMoney: state.shoppingCart.totalMoney,
      },
      productList: state.productList,
    };
  }
  if (action.type === "resetCart") {
    return {
      admin: state.admin,
      user: state.user,
      searchKey: state.searchKey,
      productList: state.productList,
      shoppingCart: {
          cartList:[],
          totalMoney:0
      },
    };
  }
  if (action.type === "addPaymentInfo") {
    return {
      admin: state.admin,
      user: {
        personalInfo: state.user.personalInfo,
        paymentDetails: action.paymentInfo,
      },
      searchKey: state.searchKey,
      productList: state.productList,
      shoppingCart: state.shoppingCart,
    };
  }
  return state;
};
const getTotalPrice = ({ cartList }) => {
  let totalPrice = 0.0;
  {
    cartList.map((cart) => {
      let price = parseInt(cart.price);
      let quantity = parseInt(cart.quantity);
      console.log(price + "-Price, " + quantity + "quanitity");
      totalPrice += price * quantity;
    });
  }
};
const store = createStore(webshopReducer);
export default store;
