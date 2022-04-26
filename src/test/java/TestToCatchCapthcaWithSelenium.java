import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class TestToCatchCapthcaWithSelenium {
   // protected static
    WebDriver wd;

    @BeforeEach
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("/Qa29 Projects/AcrossTroughtCaptcha/src/test/resources/modheader.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
       // wd = new ChromeDriver(capabilities);
        wd = new ChromeDriver(chromeOptions);
        addCookie();

    }

    private void addCookie() {
        wd.get("http://2ip.ru");
        wd.get("chrome-extension://idgpnmonknjnojddfkpgkljpfnnfcklj/popup.html");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        wd.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("cookie");
        wd.findElement(By.xpath("//input[@placeholder='Value']")).sendKeys("lang=ru; _ga=GA1.2.69770897.1637760369; _ym_uid=1637760369714540380; _ym_d=1637760369; _gid=GA1.2.873034881.1645195768; _ym_isad=1; session=c506ba4443b0042f14f95cb74254c331; userid=14734; PHPSESSID=pogdg4ik8ojli5j2kb82lgvnod; _ym_visorc=w");
        wd.findElement(By.xpath("//input[@placeholder='Name']")).click();
           }

    @Test
    public void checkBalance() {
        wd.get("http://smshub.org/ru/activations");
        SmsApi smsApi = new SmsApi();
        String balanceUi = wd.findElement(By.xpath("//a[@id='balansUser']")).getText();
        String balanceApi = smsApi.getAccountBalance();
        Assertions.assertTrue(balanceUi.contains(balanceApi));

    }


    @AfterEach

    public void tearDown(){
        wd.quit();

    }


}
