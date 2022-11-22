package webshop.service;

public class OrderItemsDTO {
    private String productNumber;
    private int orderNumber;
    private int quantity;
    public OrderItemsDTO(){

    }


    public OrderItemsDTO(String productNumber, int quantity, int orderNumber) {
        this.productNumber = productNumber;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
