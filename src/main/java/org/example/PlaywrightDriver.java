package org.example;

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class PlaywrightDriver {
    private static final Logger logger = LoggerFactory.getLogger(PlaywrightDriver.class);
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;


    public static void initialize() {
        Properties props = new Properties();
        try (InputStream input = PlaywrightDriver.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.info("Sorry, unable to find config.properties");
                return;
            }
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String browserType = props.getProperty("browserType", "chromium");
        boolean headless = Boolean.parseBoolean(props.getProperty("headless", "true"));

        logger.info( "Initializing Playwright and " + browserType + " Browser");
        playwright = Playwright.create();
        BrowserType type = switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().
                setHeadless(headless).setArgs(List.of("--start-maximized"));

        browser = type.launch(options);
        context = browser.newContext(new Browser.NewContextOptions()
                .setLocale("en-US").setViewportSize(null));
        page = context.newPage();
        logger.info("Playwright and " + browserType + " Browser initialized");
    }

    public static void close() {
        logger.info("Closing Playwright and Browser");
        context.close();
        browser.close();
        playwright.close();
        logger.info("Browser and Playwright closed");
    }
    public static Page getPage() {
        return page;
    }
    public static Page createNewPage() {
        return context.newPage();
    }

    public static BrowserContext createNewContext() {
        return browser.newContext();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] takeScreenshot(String filePath) {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
        return screenshot;
    }

    public static void startVideoRecording(String filePath) {
        context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get(filePath)));
        page = context.newPage();
    }
}