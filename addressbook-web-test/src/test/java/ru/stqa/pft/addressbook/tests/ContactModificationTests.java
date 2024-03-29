package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        String group = "test1";
        if (app.db().contacts().size() == 0) {
            if (app.db().groupWithName(group) == null) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName(group));
            }
            app.contact().createAndFill(new ContactData().withFirstName("Natasha").withLastName("Ivanova")
               .withAddress("Moscow").withMobilePhone("89745684411").withEmail("test@testmail.ru"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Natasha")
                .withLastName("Ivanova").withAddress("Moscow").withMobilePhone("89745684411").withEmail("test@testmail.ru");
        app.goTo().homePage();
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
