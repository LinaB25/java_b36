package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() {
        app.getNavigationHelper().gotoPageGroup();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
}