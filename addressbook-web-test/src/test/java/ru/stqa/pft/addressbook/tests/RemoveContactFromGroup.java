package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RemoveContactFromGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        GroupData groupToAdd = app.db().groups().iterator().next();
        Contacts contacts = app.db().contacts();
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.contact().create(new ContactData().withFirstName("Natasha").withLastName("Ivanova")
                    .withAddress("Moscow").withMobilePhone("89745684411").withEmail("test@testmail.ru").inGroup(groupToAdd));
        }
        app.goTo().home();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() == 0) {
                app.contact().create(new ContactData().withFirstName("Natasha").withLastName("Ivanova")
                        .withAddress("Moscow").withMobilePhone("89745684411").withEmail("test@testmail.ru")
                        .inGroup(app.group().all().iterator().next()));
            }
        }
    }

    @Test
    public void testContactRemoveGroup() {
        Contacts contacts = app.db().contacts();
        ContactData selectedContact = app.contact().contactInGroup(contacts);
        ContactData before = selectedContact;
        GroupData selectedGroup = selectedGroup();
        app.contact().selectGroup(selectedGroup.getName());
        app.contact().removeSelectedContactFromGroup(app.contact().contactInGroup(contacts));
        ContactData after = before.inGroup(selectedGroup);
        assertThat(after, equalTo(before));
    }

    public GroupData selectedGroup() {
        Contacts contacts = app.db().contacts();
        return app.contact().contactInGroup(contacts).getGroups().iterator().next();
    }
}
