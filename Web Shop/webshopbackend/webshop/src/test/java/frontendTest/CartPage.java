package frontendTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertThat;

public class CartPage {

    protected WebDriver webdriver;

    public CartPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    @FindBy(className = "eachCartProduct")
    private List<WebElement> eachRowElements;
    @FindBy(id="cartValue")
    private WebElement cartValueElem;

    public void open(String url) {
        webdriver.get(url);
    }

    public void close() {
        webdriver.close();
    }

    public String removeToCartToEmptyStock() throws InterruptedException {
        Thread.sleep(1000);
        for(WebElement element: eachRowElements){
            WebElement removeTDElem = element.findElements(By.cssSelector("td")).get(3);
            removeTDElem.findElement(By.cssSelector("button")).click();
            break;
        }
        Thread.sleep(1000);
        return cartValueElem.getText();
    }





}
