package devkdt.devBook.dto;

import devkdt.devBook.entity.Post;
import lombok.Data;

@Data
public class BookDetailResponse {
    private Long id;
    private String title;
    private String summary;
    private int price;

    private int devCourse;
    private int junior;
    private int middle;


    public BookDetailResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.summary = post.getSummary();
        this.price = post.getPrice();

        this.devCourse = post.getDevCourse();
        this.junior = post.getJunior();
        this.middle = post.getMiddle();
    }

}
