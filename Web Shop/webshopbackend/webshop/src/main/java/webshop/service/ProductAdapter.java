package webshop.service;

import webshop.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter {
    public static ProductDTO getProductDTOFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        if (product != null) {
            productDTO = new ProductDTO(product.getProductNumber(), product.getProductName(), product.getPrice(), product.getNumberInStock());
        }
        return productDTO;
    }

    public static Product getProductFromDTO(ProductDTO productDTO) {
        Product product = new Product();
        if (productDTO != null) {
            product = new Product(productDTO.getProductNumber(), productDTO.getProductName(), productDTO.getPrice(), productDTO.getNumberInStock());
        }
        return product;
    }

    public static List<ProductDTO> getProductDTOList(List<Product> productList) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        if (productList != null) {
            for (Product product : productList) {
                productDTOS.add(getProductDTOFromProduct(product));
            }
        }
        return  productDTOS;
    }

}
