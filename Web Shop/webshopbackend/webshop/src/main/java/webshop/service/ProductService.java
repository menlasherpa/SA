package webshop.service;

import webshop.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.domain.Product;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

   @Autowired
   ProductRepository productRepository;

    public ProductDTO findbyProductNumber(String productNumber) {
        Optional<Product> product = productRepository.findById(productNumber);
        if(product.isPresent()){
            return ProductAdapter.getProductDTOFromProduct(product.get());
        }
        else{
            return null;
        }
    }

    public void addProduct(ProductDTO productDTO) {
        Product product = ProductAdapter.getProductFromDTO(productDTO);
        productRepository.save(product);
    }

    public void updateProduct(ProductDTO productDTO){
        Product product = ProductAdapter.getProductFromDTO(productDTO);
        productRepository.save(product);
    }

    public void removeProduct(String productNumber){
        Product product = productRepository.findById(productNumber).get();
        productRepository.delete(product);
    }

    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = ProductAdapter.getProductDTOList(products);
        return productDTOS;
    }

    public Collection<Product> findAll(){
        return productRepository.findAll();
    }

}
