package devkdt.devBook.book.dto;

import devkdt.devBook.book.domain.Book;
import lombok.Data;

@Data
public class BookInformation {

    private String title;
    private String summary;
    private int price;

    private int devCourse;
    private int junior;
    private int middle;

    public BookInformation() {
    }

    public BookInformation(String title, String summary, int price) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = 0;
        this.junior = 0;
        this.middle = 0;
    }

    public BookInformation(String title, String summary, int price, int devCourse, int junior, int middle) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public Book toBook() {
        return new Book(this.title, this.summary, this.price, this.devCourse, this.junior, this.middle);
    }

}
