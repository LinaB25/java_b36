package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        String group = "test1";
        if (!app.getContactHelper().isThereAContact()) {
            if (!app.getContactHelper().isThereAGroupToSelectWithName(group)) {
                app.getNavigationHelper().gotoPageGroup();
                app.getGroupHelper().createGroup(new GroupData(group, null, null));
            }
            app.getContactHelper().createAndFillNewContact(new ContactData("Natasha", "Ivanova", "Moscow", "89745684411", "test@testmail.ru", group));
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptDelitionContactAlert();
        app.getNavigationHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
