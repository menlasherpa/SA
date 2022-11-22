package webshop.service;

import webshop.domain.CartItem;
import webshop.domain.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter {
    public static ShoppingCartDTO getShoppingCartDTO(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        if (shoppingCart != null) {
            shoppingCartDTO = new ShoppingCartDTO(getCartItemDTOS(shoppingCart.getCartList()), shoppingCart.getTotalMoney());
        }
        return shoppingCartDTO;
    }

    public static ShoppingCart getShoppingCartFromDTO(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        if (shoppingCartDTO != null) {
            shoppingCart = new ShoppingCart(getCartItemList(shoppingCartDTO.getCartList()), shoppingCartDTO.getTotalMoney());
        }
        return shoppingCart;
    }

    public static CartItemDTO getCartItemDTO(CartItem cartItem){
        CartItemDTO cartItemDTO = new CartItemDTO();
        if(cartItem == null){
            cartItemDTO = new CartItemDTO(cartItem.getProductNumber(),cartItem.getQuantity(), cartItem.getPrice());
        }
        return  cartItemDTO;
    }
    public static CartItem getCartItem(CartItemDTO cartItemDTO){
        CartItem cartItem = new CartItem();
        if(cartItemDTO == null){
            cartItem = new CartItem(cartItem.getProductNumber(),cartItem.getQuantity(), cartItem.getPrice());
        }
        return  cartItem;
    }

    public static List<CartItemDTO> getCartItemDTOS(List<CartItem> cartItemList) {
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        if (cartItemList != null) {
            for (CartItem item : cartItemList) {
                cartItemDTOS.add(getCartItemDTO(item));
            }
        }
        return  cartItemDTOS;
    }

    public static List<CartItem> getCartItemList(List<CartItemDTO> cartItemListDtos) {
        List<CartItem> cartItems = new ArrayList<>();
        if (cartItemListDtos != null) {
            for (CartItemDTO item : cartItemListDtos) {
                cartItems.add(getCartItem(item));
            }
        }
        return  cartItems;
    }

}
