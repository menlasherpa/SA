package frontendTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class ProjectTest {
    private static ProductListPage page;
    private static CartPage cartPage;

    @Before
    public void createWebDriver() {
        // set path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "/Users/kmhira/Desktop/chromedriver");
        page = PageFactory.initElements(new ChromeDriver(), ProductListPage.class);
        page.open("http://localhost:3000/");
    }
    @After
    public  void closeTheBrowser() {
        page.close();
    }

    @Test
    public void singleProductPage() throws InterruptedException {
        SingleProductPage singlePage = page.submitProductpageButton();
        assertTrue(singlePage.verifyId());
    }

    @Test
    public void emptyStockTest() throws InterruptedException {
        String msg = page.submitAddToCartToEmptyStock();
        assertThat(msg,containsString("Sorry, Product is not in stock!!"));
    }
    @Test
    public void incCartItemValueTest() throws InterruptedException {
        String initialCartValue = page.getIntialCartValue();
        String cartValue = page.submitAddToCartButtonCheckCartIncrValue();
        int diff = Integer.parseInt(cartValue)-Integer.parseInt(initialCartValue);
        assertEquals(1,diff);
    }

    @Test
    public void checkSearchFunctionality() throws InterruptedException {
       page.sendSearchValue("Iphone");
       assertTrue(page.checkSearchItemInList("Iphone"));

    }

    @Test
    public void removeFromCartFunctionality() throws InterruptedException {
        CartPage cartPage = page.viewShopCartButton();
        String cartValue = cartPage.removeToCartToEmptyStock();
        assertEquals("0",cartValue);
    }

}
