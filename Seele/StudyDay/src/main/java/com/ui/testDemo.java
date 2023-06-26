package com.ui;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class testDemo {
    public static void main(String[] args) throws InterruptedException {
        SafariDriver sd = new SafariDriver();
        sd.get("https://www.bilibili.com");
        WebElement we = new WebDriverWait(sd, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.className("header-login-entry")));
        sd.findElement(By.className("header-login-entry")).click();
        System.out.println(we.getText());
        // 隐式等待
        sd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        sd.findElement(By.cssSelector("body > div.bili-mini-mask > div > div.bili-mini-login-right-wp > div.login-pwd-wp > form > div:nth-child(1) > input[type=\"text\"]"))
                        .sendKeys("16639941219");
        sd.findElement(By.cssSelector("body > div.bili-mini-mask > div > div.bili-mini-login-right-wp > div.login-pwd-wp > form > div:nth-child(3) > input[type=\"password\"]"))
                        .sendKeys("sakura,521");
        sd.findElement(By.xpath("/html/body/div[3]/div/div[4]/div[2]/div[2]/div[2]")).click();
        Thread.sleep(3000);
        sd.quit();
    }
}
