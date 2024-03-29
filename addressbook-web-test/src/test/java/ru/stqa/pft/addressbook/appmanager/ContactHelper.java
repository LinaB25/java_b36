package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submit() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            if(contactData.getGroups().size()>0){
                Assert.assertTrue(contactData.getGroups().size()==1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText
                        (contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresented(By.name("new_group")));
        }
    }

    public void addNew() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        addNew();
        fillContactForm(contact, true);
        submit();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitModificationContact();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //  wd.findElement(By.xpath(String.format("//input[@value='@s']/../../td[8]/a", id))).click();
        // wd.findElement(By.xpath(String.format("//tr[.//input[@value='@s']]/td[8]/a", id))).click();
        // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitModificationContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        acceptDelitionContactAlert();
    }

    public void acceptDelitionContactAlert() {
        wd.switchTo().alert().accept();
    }

    public void createAndFill(ContactData contact) {
        addNew();
        fillContactForm(contact, true);
        submit();
    }

    public boolean isThereAContact() {
        return isElementPresented(By.name("selected[]"));
    }

    public boolean isThereAGroupToSelectWithName(String groupToFind) {
        addNew();
        try {
            Select group = new Select(wd.findElement(By.name("new_group")));
            group.selectByVisibleText(groupToFind);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withMobilePhone(mobile).withHomePhone(home).withWorkPhone(work).withEmail(email).withEmail2(email2)
                .withEmail3(email3).withAddress(address).withPhone2(phone2);
    }

    public void addContactToGroup(String groupId) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(groupId);
        click(By.cssSelector("input[name='add']"));
    }

    public ContactData contactInGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contactInGroup = contact.getGroups();
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        return null;
    }

    public void selectGroup(String name){
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
    }

    public void removeSelectedContactFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        click(By.cssSelector("input[name='remove']"));
    }
}
