package frontendTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingleProductPage {
    protected WebDriver webdriver;
    protected String id;

    public SingleProductPage(WebDriver webdriver, String id) {
        this.webdriver = webdriver;
        this.id= id;
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(id = "productNumber")
    private WebElement productNumber;



    public void open(String url) {
        webdriver.get(url);
    }

    public void close() {
        webdriver.close();
    }

    public boolean verifyId(){
        if(productNumber.getText().equals(id)){
            return true;
        }
        return false;
    }


}
