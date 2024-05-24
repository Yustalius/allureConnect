package Config;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Provider {

    private final ProviderConfig config;

    public Provider() {
        this.config = ConfigFactory.create(ProviderConfig.class, System.getProperties());
        testConfiguration();
    }

    public void testConfiguration() {
        switch (config.getBrowser().toLowerCase()) {
            case "firefox":
                Configuration.browser = "firefox";
                break;
            case "chrome":
                Configuration.browser = "chrome";
                break;
            default:
                throw new RuntimeException("Browser was not chosen");
        }

        switch (config.getRemoteOrLocal()) {
            case "remote":
                Configuration.remote = config.getRemoteUrl();

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.browserCapabilities = capabilities;

                break;
            case "local":
                break;
            default:
                break;
        }

        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = 100000;
        Configuration.pageLoadTimeout = 100000;
        System.out.println("it works");

    }

}
