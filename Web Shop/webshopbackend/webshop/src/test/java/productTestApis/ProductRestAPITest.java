package productTestApis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import webshop.domain.Product;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class ProductRestAPITest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneProduct() {
        Product product = new Product("100", "Iphone-12", 1200.0,3);
        Product product1 = new Product("101","Iphone-9", 700.50, 10);
        // add the product to be fetched
        given()
                .contentType("application/json")
                .body(product)
                .when().post("/products").then()
                .statusCode(200);
        given()
                .when()
                .get("products/100")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("productNumber",equalTo("100"))
                .body("productName",equalTo("Iphone-12"))
                .body("price",equalTo(1200.0f))
                .body("numberInStock",equalTo(3));
        //cleanup
        given()
                .when()
                .delete("products/100");
    }
    @Test
    public void testAddProduct() {
        Product product = new Product("100", "Iphone-12", 1200.0,3);
        Product product1 = new Product("101","Iphone-9", 700.50, 10);
        // add the product to be fetched
        given()
                .contentType("application/json")
                .body(product)
                .when().post("/products").then()
                .statusCode(200);
        given()
                .when()
                .get("products/100")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("productNumber",equalTo("100"))
                .body("productName",equalTo("Iphone-12"))
                .body("price",equalTo(1200.0f))
                .body("numberInStock",equalTo(3));
        //cleanup
        given()
                .when()
                .delete("products/100");
    }
    @Test
    public void testDeleteProduct() {
        Product product = new Product("100", "Iphone-12", 1200.0,3);
        // add the product to be fetched
        given()
                .contentType("application/json")
                .body(product)
                .when().post("/products").then()
                .statusCode(200);
        given()
                .when()
                .delete("products/100");

        given()
                .when()
                .get("products/100")
                .then()
                .statusCode(404)
                .and()
                .body("errorMsg",equalTo("Product with number: 100is not available"));

    }
    @Test
    public void testUpdateProduct() {
        // add the book
        Product product = new Product("100", "Iphone-12", 1200.0,3);
        Product updateProduct = new Product("100", "Iphone-12", 1200.0,12);
        given()
                .contentType("application/json")
                .body(product)
                .when().post("/products").then()
                .statusCode(200);
        //update book
        given()
                .contentType("application/json")
                .body(updateProduct)
                .when().put("/products/"+updateProduct.getProductNumber()).then()
                .statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("products/100")
                .then()
                .statusCode(200)
                .and()
                .body("productNumber",equalTo("100"))
                .body("productName",equalTo("Iphone-12"))
                .body("price",equalTo(1200.0f))
                .body("numberInStock",equalTo(12));
        //cleanup
        given()
                .when()
                .delete("books/100");
    }

}
