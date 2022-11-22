package webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.data.OrderRepository;
import webshop.domain.Order;
import webshop.domain.OrderItems;
import webshop.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(ShoppingCartDTO shoppingCartDTO, int orderNumber) {
        List<CartItemDTO> cartItemDTOs = shoppingCartDTO.getCartList();
        List<OrderItemsDTO> orderItemsDTOs = new ArrayList<>();
        for (CartItemDTO cartItemDTO : cartItemDTOs) {
            orderItemsDTOs.add(new OrderItemsDTO(cartItemDTO.getProductNumber(), cartItemDTO.getQuantity(), (orderNumber)));
        }
        OrderDTO orderDTO = new OrderDTO(orderNumber,"PENDING", shoppingCartDTO.getTotalMoney(), orderItemsDTOs);
        orderRepository.save(OrderAdapter.getOrderFromDTOS(orderDTO));
    }

    public int getHighestOrderNumber() {
        Order order = orderRepository.findFirstByOrderByOrderNumberDesc();
        if (order == null) {
            return 1;
        } else {
            return order.getOrderNumber()+1;
        }
    }

    public List<OrderDTO> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = OrderAdapter.getOrderListDTO(orders);
        return orderDTOS;
    }

    public void updateOrder(OrderDTO orderDTO, String status){
        Order order = OrderAdapter.getOrderFromDTOS(orderDTO);
        Order orderDocument = orderRepository.findByOrderNumber(order.getOrderNumber());
        System.out.println(orderDocument+"Document");
        orderDocument.setStatus(status);
        orderRepository.save(orderDocument);
        System.out.println(orderDocument+"SavedDocument");
    }

    public OrderDTO findbyOrderNumber(int orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order!=null) {
            return OrderAdapter.getOrderDTOFromOrder(order);
        } else {
            return null;
        }
    }



    public void removeOrder(int orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        orderRepository.delete(order);
    }

}
