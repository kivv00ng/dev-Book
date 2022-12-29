package devkdt.devBook.dto;


import devkdt.devBook.entity.Post;
import lombok.Data;

@Data
public class BookAddRequest {

    private String title;
    private String summary;
    private int price;

    private int devCourse;
    private int junior;
    private int middle;

    public BookAddRequest() {
    }

    public BookAddRequest(String title, String summary, int price) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = 0;
        this.junior = 0;
        this.middle = 0;
    }

    public BookAddRequest(String title, String summary, int price, int devCourse, int junior, int middle) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public Post toPost() {
        return new Post(this.title, this.summary, this.price, this.devCourse, this.junior, this.middle);
    }
}
