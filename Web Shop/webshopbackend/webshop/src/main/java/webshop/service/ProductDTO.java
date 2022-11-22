package webshop.service;

public class ProductDTO {
    private String productNumber;
    private String productName;
    private double price;
    private int numberInStock;

    public ProductDTO(){

    }
    public ProductDTO(String productNumber, String productName, double price, int numberInStock) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.numberInStock = numberInStock;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }
}
