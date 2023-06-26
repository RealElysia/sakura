package com.base;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.By;

public class findFunction {
    SafariDriver safari = new SafariDriver();
    public void urlGet(String url){
        safari.get(url);
        safari.manage().window().fullscreen();
    }
    // ID定位
    public void findById(String id, String kw){
        safari.findElement(By.id(id)).sendKeys(kw);
    }
    public void findById(String id){
        safari.findElement(By.id(id)).click();
    }
    // class_name 定位
    public void findByClass(String clsName, String send){
        safari.findElement(By.className(clsName)).sendKeys(send);
    }
    public void findByClass(String clsName){
        safari.findElement(By.className(clsName)).click();
    }
    // xpath 定位
    public void findByXpath(String xName, String send){
        safari.findElement(By.xpath(xName)).sendKeys(send);
    }
    public void findByXpath(String xName){
        safari.findElement(By.xpath(xName)).click();
    }
    // css 定位
    public void findByCss(String cssName, String send){
        safari.findElement(By.cssSelector(cssName)).sendKeys(send);
    }
    public void findByCss(String cssName){
        safari.findElement(By.cssSelector(cssName)).click();
    }

    public void quit(){
        safari.quit();
    }
    public void clean(String element){safari.findElementById(element).clear();}
}
