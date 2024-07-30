package pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class config {
    private static JSONObject config;
    private static JSONObject credentials;

    static {
        try (FileReader configReader = new FileReader("C:\\Users\\Admin\\IdeaProjects\\Assignment4_C\\src\\main\\java\\config.json");
             FileReader credentialsReader = new FileReader("C:\\Users\\Admin\\IdeaProjects\\Assignment4_C\\src\\main\\java\\credentials.json")) {
            JSONParser parser = new JSONParser();
            config = (JSONObject) parser.parse(configReader);
            credentials = (JSONObject) parser.parse(credentialsReader);
        } catch (IOException e) {
            System.err.println("IOException while reading the configuration files: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("ParseException while parsing the JSON files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getBrowser() {
        return (String) config.get("browser");
    }

    public static String getGridUrl() {
        String url = (String) config.get("gridUrl");
        if (url == null) {
            System.err.println("Grid URL is not set in the configuration file.");
        }
        return url;
    }

    public static String getApplicationUrl() {
        return (String) config.get("applicationUrl");
    }

    public static String getChromeDriverPath() {
        return (String) config.get("chromedriver.path");
    }

    public static String getGeckoDriverPath() {
        return (String) config.get("geckodriver.path");
    }

    public static String getEmail() {
        return (String) credentials.get("email");
    }

    public static String getCardNumber() {
        return (String) credentials.get("cardNumber");
    }

    public static String getExpiry() {
        return (String) credentials.get("expiry");
    }

    public static String getCVV() {
        return (String) credentials.get("cvv");
    }

    public static String getZipCode() {
        return (String) credentials.get("zipCode");
    }
}
