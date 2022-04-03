package cz.spsmb.service.http;

import cz.spsmb.gui.window.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebsiteCheck {

    private static Logger logger = LogManager.getLogger(WebsiteCheck.class);
    public static boolean isAccessable(String url, int timeout) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                logger.error("Can't connect (" + url + ") - refreshing in next Loop");
                Window.infoBox("Website timeout (" + timeout + ").\nCan't establish a connection at " + url + "\n\nTry checking your internet connection or if site works", "Unable to connect " + url);
                return false;
            }
        } catch (IOException exception) {
            logger.error("Unexpected error (" + url + "): " + exception);
            Window.errorBox(exception + "\nurl: " + url, "Check if the site isn't redirecting to different site or if the site exists", WebsiteCheck.class.toString());
            System.exit(0);
        }
        logger.info("Success");
        return true;
    }
}
