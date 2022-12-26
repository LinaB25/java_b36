package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createAndFillNewContact(new ContactData("Natasha", "Ivanova", "Moscow", "89745684411", "test@testmail.ru", "test1"));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Natasha", "Petrova", "Moscow", "89745684411", "test@testmail.ru", null), false);
        app.getContactHelper().submitModificationContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
