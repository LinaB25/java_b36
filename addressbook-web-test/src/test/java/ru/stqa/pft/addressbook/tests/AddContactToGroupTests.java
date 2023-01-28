package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().home();
            app.contact().createAndFill(new ContactData().withFirstName("Natasha").withLastName("Ivanova")
                    .withAddress("Moscow").withMobilePhone("89745684411").withEmail("test@testmail.ru"));
        }
    }

    @Test
    public void addContactToGroup() {
        Contacts contacts = app.db().contacts();
        ContactData selectedContact = contactToAddToGroup(contacts);
        ContactData before = selectedContact;
        if (selectedContact == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("new group"));
            selectedContact = contactToAddToGroup(contacts);
            before = selectedContact;
        }
        app.goTo().home();
        app.contact().selectContactById(selectedContact.getId());
        GroupData groupToAdd = groupWithoutContact();
        app.contact().addContactToGroup(String.valueOf(groupToAdd.getId()));
        ContactData after = selectedContact.inGroup(groupToAdd);
        assertThat(after, equalTo(before));

    }

    public ContactData contactToAddToGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contactInGroup = contact.getGroups();
            if (contactInGroup.size() < app.db().groups().size()) {
                return contact;
            }
        }
        return null;
    }

    public GroupData groupWithoutContact() {
        Contacts contacts = app.db().contacts();
        Groups groupsInContact = contactToAddToGroup(contacts).getGroups();
        Groups groups = app.db().groups();
        groups.removeAll(groupsInContact);
        GroupData group = groups.iterator().next();
        return group;
    }
}
