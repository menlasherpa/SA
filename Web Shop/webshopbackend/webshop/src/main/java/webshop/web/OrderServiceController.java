package webshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.domain.ShoppingCart;
import webshop.service.*;

import java.util.List;

@CrossOrigin
@RestController
public class OrderServiceController {
    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody ShoppingCartDTO shoppingcartDto){
        System.out.println(shoppingcartDto);
        int orderNumber = orderService.getHighestOrderNumber();
        orderService.saveOrder(shoppingcartDto, orderNumber);
        OrderDTO orderDTO = orderService.findbyOrderNumber(orderNumber);
        System.out.println(shoppingcartDto);
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(){
        List<OrderDTO> orderDTOSList = orderService.findAllOrders();
        return new ResponseEntity<List<OrderDTO>>(orderDTOSList, HttpStatus.OK);
    }
    @DeleteMapping("/orders/{orderNumber}")
    public ResponseEntity<?> deleteOrder(@PathVariable int orderNumber){
        OrderDTO orderDto = orderService.findbyOrderNumber(orderNumber);
        if(orderDto==null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Order with number: "+orderNumber+" is not available"), HttpStatus.NOT_FOUND);
        }
        orderService.removeOrder(orderNumber);
        return new ResponseEntity<String>("Deleted",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/orders/{orderNumber}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable int orderNumber, @PathVariable String status){
        OrderDTO orderDto = orderService.findbyOrderNumber(orderNumber);
        if(orderDto==null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Order with number: "+orderNumber+" is not available"), HttpStatus.NOT_FOUND);
        }
        System.out.println(orderDto);
        orderService.updateOrder(orderDto, status);
        OrderDTO orderDtoResponse = orderService.findbyOrderNumber(orderNumber);
        return new ResponseEntity<OrderDTO>(orderDtoResponse,HttpStatus.OK);
    }

    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<?> getOneOrder(@PathVariable int orderNumber){
        OrderDTO orderDTO =  orderService.findbyOrderNumber(orderNumber);
        if(orderDTO==null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Order with number: "+orderNumber+"is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }

}
