package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        String group = "test1";
        if (app.db().contacts().size() == 0) {
            if (app.db().groupWithName(group) == null) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName(group));
            }
            app.contact().createAndFill(new ContactData().withFirstName("Natasha").withLastName("Ivanova")
                    .withAddress("Moscow").withMobilePhone("89745684411").withEmail("test@testmail.ru").withGroup(group));
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().home();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
