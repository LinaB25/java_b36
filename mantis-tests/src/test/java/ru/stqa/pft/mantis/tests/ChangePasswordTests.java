package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }
    @Test
    public void testChangePassword() throws IOException {
        String newPassword = "pass1";
        app.changePWhelper().loginAsAdmin();
        app.changePWhelper().goToManagePage();
        app.changePWhelper().goToManageUsers();
        app.changePWhelper().goToUser();
        String user = app.changePWhelper().getUserName();
        String email = String.format("%s@localhost.localdomain", user);
        app.changePWhelper().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(user, newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
