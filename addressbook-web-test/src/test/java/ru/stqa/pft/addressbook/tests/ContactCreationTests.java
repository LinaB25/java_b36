package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testNewContact() {
        app.getContactHelper().createContact();
        app.getContactHelper().fillContactForm(new ContactData("Natasha", "Ivanova", "Moscow", "89745684411", "test@testmail.ru", "test1"), true);
        app.getContactHelper().submit();
    }
}