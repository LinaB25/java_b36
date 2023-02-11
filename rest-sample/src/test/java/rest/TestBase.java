package rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {

    private String issueStatus;
    public boolean isIssueOpened(int issueId) {
        String status = issueStatus(issueId);
        if (!status.equals("Resolved")) {
            return true;
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpened(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId + " was not fixed");
        }
    }

    public String issueStatus(int id) {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + id + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        issueStatus = issue.iterator().next().getState_name();
        return issueStatus;
    }
}
