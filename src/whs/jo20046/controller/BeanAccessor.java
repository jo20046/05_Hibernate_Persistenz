package whs.jo20046.controller;

import whs.jo20046.beans.Data;

public class BeanAccessor {

    private Data data;

    public BeanAccessor(Data data) {
        this.data = data;
    }


    public String getUrl(int index) {
        return index >= 0 && index < data.getUrls().size() ? data.getUrls().get(index) : "Index " + index + " out of bounds";
    }

    public void addUrl(String newUrl) {
        data.getUrls().add(newUrl);
    }

    public void clearUrls() {
        data.getUrls().clear();
    }

    public void addNotFound(int newValue) {
        data.getNotFound().add(newValue);
    }

    public void removeNotFound(int val) {
        data.getNotFound().remove(val);
    }

    public void clearNotFound() {
        data.getNotFound().clear();
    }

    public void clearArticles() {
        data.setArticles("");
    }
}
