import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.example.PlaywrightDriver;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Properties;

public class MyPlaywrightTest {
    @BeforeAll
    public static void setup() {
        PlaywrightDriver.initialize();
    }
    @AfterAll
    public static void teardown() {
        PlaywrightDriver.close();
    }


//    @ParameterizedTest
    @Feature("Example Feature")
    @Story("Example Story")
    @Severity(SeverityLevel.NORMAL)
    @Description("This is another example test case with new context")
    @Test
    public void testExample() {
        Page page = PlaywrightDriver.getPage();
        page.navigate("https://example.com");
        Assertions.assertEquals("Example Domain", page.title());
        PlaywrightDriver.takeScreenshot("screenshot_" + System.currentTimeMillis() + ".png");
    }

}
