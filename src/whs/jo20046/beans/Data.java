package whs.jo20046.beans;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

//@Entity
public class Data {

    //    protected Long id;
    private ArrayList<String> urls = new ArrayList<>();
    private Set<Integer> notFound = new LinkedHashSet<>();
    private String articles;
    private String notFoundUrls;
    private int sources;

    public Data() {
    }

//    @Id
//    @GeneratedValue
//    public Long getId() {return id;}
//    public void setId(Long id) {this.id = id;}

    //    @Transient
    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    //    @Transient
    public Set<Integer> getNotFound() {
        return notFound;
    }

    public void setNotFound(Set<Integer> notFound) {
        this.notFound = notFound;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
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
