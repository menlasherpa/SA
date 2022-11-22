package webshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.service.ProductDTO;
import webshop.service.ProductService;


import java.util.List;

@CrossOrigin
@RestController
public class  ProductServiceController {
    @Autowired
    ProductService productService;

    @GetMapping("/products/{productNumber}")
    public ResponseEntity<?> getProduct(@PathVariable String productNumber){
       ProductDTO productDTO =  productService.findbyProductNumber(productNumber);
       if(productDTO==null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Product with number: "+productNumber+"is not available"), HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @PutMapping("/products/{productNumber}")
    public ResponseEntity<?> updateProduct(@PathVariable String productNumber, @RequestBody ProductDTO productDTO){
        productService.updateProduct(productDTO);
        return new ResponseEntity<ProductDTO> (productDTO, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> productList = productService.findAllProducts();
        return new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productNumber}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productNumber){
        ProductDTO productDTO = productService.findbyProductNumber(productNumber);
        if(productDTO==null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Product with number: "+productNumber+" is not available"), HttpStatus.NOT_FOUND);
        }
        productService.removeProduct(productNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
