package org.example;

import java.lang.*;
import java.nio.file.Paths;

import com.microsoft.playwright.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            Page page = browser.newPage();
            page.navigate("https://playwright.dev/");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        }
    }
}