package webshop.service;

import webshop.domain.Order;
import webshop.domain.OrderItems;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter {
    public static OrderDTO getOrderDTOFromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        if (order != null) {
            orderDTO = new OrderDTO(order.getOrderNumber(),order.getStatus(),order.getOrderPrice(), getListOrderItemDTO(order.getOrderItemsList()));
        }
        return orderDTO;
    }

    public static Order getOrderFromDTOS(OrderDTO orderDTO) {
        Order order = new Order();
        if (orderDTO != null) {
            order = new Order(orderDTO.getOrderNumber(),orderDTO.getStatus(), orderDTO.getOrderPrice(), getOrderLISTFromDTOLIST(orderDTO.getOrderItemsList()));
        }
        return order;
    }

    public static OrderItemsDTO getOrderItemsDTO(OrderItems orderItems) {
        OrderItemsDTO OrderItemsDTO = new OrderItemsDTO();
        if (orderItems != null) {
            OrderItemsDTO = new OrderItemsDTO( orderItems.getProductNumber(), orderItems.getQuantity(), orderItems.getOrderNumber());
        }
        return OrderItemsDTO;
    }

    public static OrderItems getOrderItemFromDTOS(OrderItemsDTO orderItemsDTO) {
        OrderItems orderItems = new OrderItems();
        if (orderItemsDTO != null) {
            orderItems = new OrderItems(orderItemsDTO.getProductNumber(), orderItemsDTO.getQuantity(), orderItemsDTO.getOrderNumber());
        }
        return orderItems;
    }

    public static List<OrderItemsDTO> getListOrderItemDTO(List<OrderItems> orderItemsList) {
        List<OrderItemsDTO> orderItemsDTOS = new ArrayList<>();
        if (orderItemsList != null) {
            for (OrderItems item : orderItemsList) {
                orderItemsDTOS.add(getOrderItemsDTO(item));
            }
        }
        return  orderItemsDTOS;
    }
    public static List<OrderItems> getOrderLISTFromDTOLIST(List<OrderItemsDTO> orderItemsDTOS) {
        List<OrderItems> orderItems = new ArrayList<>();
        if (orderItemsDTOS != null) {
            for (OrderItemsDTO item : orderItemsDTOS) {
                orderItems.add(getOrderItemFromDTOS(item));
            }
        }
        return  orderItems;
    }
    public static List<OrderDTO> getOrderListDTO(List<Order> orders) {
        List<OrderDTO> orderItemsDTOS = new ArrayList<>();
        if (orders != null) {
            for (Order item : orders) {
                orderItemsDTOS.add(getOrderDTOFromOrder(item));
            }
        }
        return  orderItemsDTOS;
    }
}
