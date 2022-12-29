package devkdt.devBook.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @OneToMany(mappedBy = "post")
    private List<Evaluation> evaluations = new ArrayList<>();

    private String title;
    private String summary;
    private int price;

    private int devCourse;
    private int junior;
    private int middle;

    protected Post() {
    }

    public Post(String title, String summary, int price) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = 0;
        this.junior = 0;
        this.middle = 0;
    }

    public Post(String title, String summary, int price, int devCourse, int junior, int middle) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public Long getId() {
        return id;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public int getPrice() {
        return price;
    }

    public int getDevCourse() {
        return devCourse;
    }

    public int getJunior() {
        return junior;
    }

    public int getMiddle() {
        return middle;
    }


    public void addDevCourse() {
        this.devCourse++;
    }

    public void addJunior() {
        this.junior++;
    }

    public void addMiddle() {
        this.middle++;
    }
}
