package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testNewContact() {
        String group = "test1";
        if (!app.getContactHelper().isThereAGroupToSelectWithName(group)) {
            app.getNavigationHelper().gotoPageGroup();
            app.getGroupHelper().createGroup(new GroupData(group, null, null));
            app.getContactHelper().createContact();
        }
        app.getNavigationHelper().gotoHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact();
        ContactData contact = new ContactData("Natasha", "Ivanova", "Moscow", "89745684411", "test@testmail.ru", group);
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submit();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}