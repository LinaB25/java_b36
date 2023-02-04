package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.xpath("//input[@value='Login']"));
    }

    public void goToManagePage() {
        click(By.xpath("//a[contains(text(),'Manage')]"));
    }

    public void goToManageUsers() {
        click(By.xpath("//a[contains(text(),'Manage Users')]"));
    }

    public void goToUser() {
        click(By.xpath("//tr[2]/td/a"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public String getUserName() {
        String user = wd.findElement(By.name("username")).getAttribute("value");
        return user;
    }
}

