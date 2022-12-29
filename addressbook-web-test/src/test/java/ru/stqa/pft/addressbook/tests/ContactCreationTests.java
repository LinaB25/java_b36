package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        if(!app.getContactHelper().isThereAGroupToSelect()){
            app.getNavigationHelper().gotoPageGroup();
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            app.getContactHelper().createContact();
        }
        app.getContactHelper().createContact();
        app.getContactHelper().fillContactForm(new ContactData("Natasha", "Ivanova", "Moscow", "89745684411", "test@testmail.ru", "test1"), true);
        app.getContactHelper().submit();
    }
}