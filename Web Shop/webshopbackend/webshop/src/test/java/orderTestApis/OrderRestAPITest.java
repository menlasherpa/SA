package orderTestApis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import webshop.domain.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderRestAPITest {
    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testCreateOrder() {
        CartItem cartItem = new CartItem("100", 3, 300);
        CartItem cartItem1 = new CartItem("101", 1, 400);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        cartItemList.add(cartItem1);
        ShoppingCart shoppingCart = new ShoppingCart(cartItemList, 1300);

        // add the product to be fetched
        given()
                .contentType("application/json")
                .body(shoppingCart)
                .when().post("/orders").then()
                .statusCode(200);
        Order[] orders = given()
                .when()
                .get("orders/")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(Order[].class);
        for(Order order: orders){
            for(OrderItems item: order.getOrderItemsList()){
                if (item.getProductNumber().equals("100")){
                    Assert.assertEquals(item.getQuantity(),3);
                    Assert.assertEquals(item.getOrderNumber(),order.getOrderNumber());
                    given().when().delete("orders/"+order.getOrderNumber());
                }
            }

        }

    }
    @Test
    public void testStatusChangeOrder() {
        CartItem cartItem = new CartItem("100", 3, 300);
        CartItem cartItem1 = new CartItem("101", 1, 400);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        cartItemList.add(cartItem1);
        ShoppingCart shoppingCart = new ShoppingCart(cartItemList, 1300);

        // add the order to be fetched
       Order aOrder = given()
                .contentType("application/json")
                .body(shoppingCart)
                .when().post("/orders").then()
                .statusCode(200)
                .extract()
                .as(Order.class);
        Order updateOrder = given()
                .contentType("application/json")
                .body(shoppingCart)
                .when().put("/orders/"+ aOrder.getOrderNumber()+"/SHIPPED").then()
                .statusCode(200)
                .extract()
                .as(Order.class);

        Assert.assertEquals("SHIPPED", updateOrder.getStatus());

    }
    @Test
    public void testgetOneOrder() {
        CartItem cartItem = new CartItem("100", 3, 300);
        CartItem cartItem1 = new CartItem("101", 1, 400);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        cartItemList.add(cartItem1);
        ShoppingCart shoppingCart = new ShoppingCart(cartItemList, 1300);

        // add the order to be fetched
        Order aOrder = given()
                .contentType("application/json")
                .body(shoppingCart)
                .when().post("/orders").then()
                .statusCode(200)
                .extract()
                .as(Order.class);
        Order getOrder = given()
                .contentType("application/json")
                .body(shoppingCart)
                .when().get("/orders/"+ aOrder.getOrderNumber()).then()
                .statusCode(200)
                .extract()
                .as(Order.class);

        Assert.assertEquals(1300.0f, getOrder.getOrderPrice(), 0.000001);
        Assert.assertEquals(getOrder.getOrderItemsList().size(), getOrder.getOrderItemsList().size());
        Assert.assertEquals(getOrder.getOrderItemsList(), getOrder.getOrderItemsList());

    }
}
