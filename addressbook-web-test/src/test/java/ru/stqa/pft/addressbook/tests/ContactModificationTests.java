package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        String group = "test1";
        if(!app.getContactHelper().isThereAContact()){
            if(!app.getContactHelper().isThereAGroupToSelectWithName(group)){
                app.getNavigationHelper().gotoPageGroup();
                app.getGroupHelper().createGroup(new GroupData(group, null, null));
            }
            app.getContactHelper().createAndFillNewContact(new ContactData("Natasha", "Ivanova", "Moscow", "89745684411", "test@testmail.ru", group));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Natasha", "Petrova", "Moscow", "89745684411", "test@testmail.ru", null), false);
        app.getContactHelper().submitModificationContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
