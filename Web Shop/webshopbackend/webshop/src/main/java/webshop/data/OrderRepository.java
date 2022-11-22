package webshop.data;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import webshop.domain.Order;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findFirstByOrderByOrderNumberDesc();
    Order findByOrderNumber(int orderNumber);

}