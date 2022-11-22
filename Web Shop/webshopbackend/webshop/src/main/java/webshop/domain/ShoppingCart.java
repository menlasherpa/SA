package webshop.domain;

import java.util.List;

public class ShoppingCart {
    private List<CartItem> cartList;
    private double totalMoney;

    public ShoppingCart(){

    }
    public ShoppingCart(List<CartItem> cartList, double totalMoney) {
        this.cartList = cartList;
        this.totalMoney = totalMoney;
    }

    public List<CartItem> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartItem> cartList) {
        this.cartList = cartList;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartList=" + cartList +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
