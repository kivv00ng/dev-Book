package devkdt.devBook.book.dto;

import devkdt.devBook.book.domain.Book;
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


    public BookDetailResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.summary = book.getSummary();
        this.price = book.getPrice();

        this.devCourse = book.getDevCourse();
        this.junior = book.getJunior();
        this.middle = book.getMiddle();
    }

}
