package webshop.service;

import webshop.domain.CartItem;

import java.util.List;

public class ShoppingCartDTO {
    private List<CartItemDTO> cartList;
    private double totalMoney;

    public ShoppingCartDTO(){

    }
    public ShoppingCartDTO(List<CartItemDTO> cartList, double totalMoney) {
        this.cartList = cartList;
        this.totalMoney = totalMoney;
    }

    public List<CartItemDTO> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartItemDTO> cartList) {
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
        return "ShoppingCartDTO{" +
                "cartList=" + cartList +
                ", totalMoney=" + totalMoney +
                '}';
    }
}

