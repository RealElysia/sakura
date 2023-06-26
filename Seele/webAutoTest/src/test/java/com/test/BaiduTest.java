package com.test;

import com.base.*;
import org.testng.annotations.Test;

import java.util.List;

public class BaiduTest {
    excelTest excel = new excelTest();
    findFunction find = new findFunction();
    List<String> list = excel.readExcel("src/main/java/com/base/element.xlsx");

    @Test
    public void taoTe() throws InterruptedException{
        // e+x: 第x个元素， s+x：第x个要输入的内容
        String e1 = list.get(1);
        String s1 = list.get(2);
        String e2 = list.get(3);
        String s2 = list.get(4);
        // 淘特广告平台
        String url = list.get(0);
        find.urlGet(url);
        Thread.sleep(5000);
        find.findByCss(e1, s1);
        find.findByCss(e2, s2);
        Thread.sleep(3000);
        find.quit();
    }
}
