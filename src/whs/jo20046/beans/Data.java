package whs.jo20046.beans;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Data {

    private ArrayList<String> urls = new ArrayList<>();
    private Set<Integer> notFound = new LinkedHashSet<>();
    private String articles;
    private String notFoundUrls;
    private int sources;

    public Data() {
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    public String getUrl(int index) {
        return index >= 0 && index < urls.size() ? urls.get(index) : "Index " + index + " out of bounds";
    }

    public void setUrl(int index, String newValue) {
        urls.set(index, newValue);
    }

    public void addUrl(String newUrl) {
        urls.add(newUrl);
    }

    public void clearUrls() {
        urls.clear();
    }

    public Set<Integer> getNotFound() {
        return notFound;
    }

    public void setNotFound(Set<Integer> notFound) {
        this.notFound = notFound;
    }

    public boolean notFoundContains(int val) {
        return notFound.contains(val);
    }

    public void addNotFound(int newValue) {
        notFound.add(newValue);
    }

    public void removeNotFound(int val) {
        notFound.remove(val);
    }

    public void clearNotFound() {
        notFound.clear();
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public void clearArticles() {
        articles = "";
    }

    public String getNotFoundUrls() {
        return notFoundUrls;
    }

    public void setNotFoundUrls(String notFoundUrls) {
        this.notFoundUrls = notFoundUrls;
    }

    public int getSources() {
        return sources;
    }

    public void setSources(int sources) {
        this.sources = sources;
    }
}
