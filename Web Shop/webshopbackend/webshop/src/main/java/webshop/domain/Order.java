package webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Order {
    @Id
    private int orderNumber;
    private double orderPrice;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderItems> orderItemsList;

    public Order() {

    }

    public Order(int orderNumber, String status, double orderPrice, List<OrderItems> orderItemsList) {
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

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", orderPrice=" + orderPrice +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", orderItemsList=" + orderItemsList +
                '}';
    }
}
