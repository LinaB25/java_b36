package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoPageGroup() {
        if (isElementPresented(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresented(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));

    }

    public void gotoHomePage() {
        if (isElementPresented(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

}
