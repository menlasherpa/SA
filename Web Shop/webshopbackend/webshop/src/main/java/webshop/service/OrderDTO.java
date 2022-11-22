package webshop.service;

import webshop.domain.OrderItems;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private int orderNumber;
    private double orderPrice;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderItemsDTO> orderItemsList;

    public OrderDTO(){
    }

    public OrderDTO(int orderNumber,String status, double orderPrice,  List<OrderItemsDTO> orderItemsList) {
        this.orderNumber = orderNumber;
        this.orderPrice = orderPrice;
        this.status = status;
        this.orderDate = LocalDateTime.now();
        this.orderItemsList = orderItemsList;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemsDTO> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItemsDTO> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderNumber=" + orderNumber +
                ", orderPrice=" + orderPrice +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", orderItemsList=" + orderItemsList +
                '}';
    }
}
