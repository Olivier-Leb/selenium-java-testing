package utils;

import java.util.*;

import static utils.PropertyLoader.getConfigValue;

public class Media {
    public static List<String> getCategories() {
        return Arrays.asList("movie", "serie", "album", "book", "comic", "game");
    }

    /**
     * Build each category links
     * @return List<String>
     */
    public static List<String> getLinks() {
        List<String> links = new ArrayList<>();
        List<String> categories = getCategories();
        List<String> partialLinks = Arrays.asList("", "/search", "/game/quizz");

        for (String c : categories) {
            for (String l : partialLinks) {
                links.add(getConfigValue("url.base") + "/" + c + l);
            }
        }

        return links;
    }
}
