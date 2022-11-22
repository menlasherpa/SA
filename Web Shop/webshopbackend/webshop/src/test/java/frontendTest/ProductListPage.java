package frontendTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ProductListPage {

    protected WebDriver webdriver;

    public ProductListPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    @FindBy(className = "productPage")
    private List<WebElement> productPageButtons;
    @FindBy(className = "eachProduct")
    private List<WebElement> eachRowElements;
    @FindBy(id = "errorMsg")
    private WebElement errMsg;
    @FindBy(id = "cartValue")
    private WebElement cartValueElem;
    @FindBy(name = "search")
    private WebElement searchInputBox;
    @FindBy(id = "searchButton")
    private WebElement searchButton;
    @FindBy(id = "viewShoppingCart")
    private WebElement viewShopCartButton;


    public void open(String url) {
        webdriver.get(url);
    }

    public void close() {
        webdriver.close();
    }

    public SingleProductPage submitProductpageButton() throws InterruptedException {
        Thread.sleep(1000);
        String id = productPageButtons.get(0).getAttribute("value");
        productPageButtons.get(0).click();
        return new SingleProductPage(webdriver, id);
    }

    public String submitAddToCartToEmptyStock() throws InterruptedException {
        Thread.sleep(1000);
        for (WebElement element : eachRowElements) {
            WebElement stockElement = element.findElements(By.cssSelector("td")).get(3);
            if (stockElement.getText().equals("0")) {
                element.findElements(By.cssSelector("td")).get(4).findElement(By.cssSelector("button")).click();
            }
        }
        return errMsg.getText();
    }

    public String submitAddToCartButtonCheckCartIncrValue() throws InterruptedException {
        Thread.sleep(1000);
        for (WebElement element : eachRowElements) {
            WebElement stockElement = element.findElements(By.cssSelector("td")).get(3);
            if (!stockElement.getText().equals("0")) {
                System.out.println(getIntialCartValue());
                element.findElements(By.cssSelector("td")).get(4).findElement(By.cssSelector("button")).click();
                Thread.sleep(1000);
                break;
            }
        }
        return cartValueElem.getText();
    }

    public String getIntialCartValue() {
        return cartValueElem.getText();
    }

    public boolean checkSearchItemInList(String searchKey) throws InterruptedException {
        Thread.sleep(1000);
        for (WebElement element : eachRowElements) {
            WebElement productNameElem = element.findElements(By.cssSelector("td")).get(1);
            Thread.sleep(1000);
            if (!productNameElem.getText().startsWith(searchKey)) {
                return false;
            }
        }
        return true;

    }

    public void sendSearchValue(String searchKey) {
        searchInputBox.sendKeys(searchKey);
        searchButton.click();
    }

    public CartPage viewShopCartButton() throws InterruptedException {
        String value = submitAddToCartButtonCheckCartIncrValue();
        System.out.println(value);
        viewShopCartButton.click();
        return new CartPage(webdriver);
    }


}
