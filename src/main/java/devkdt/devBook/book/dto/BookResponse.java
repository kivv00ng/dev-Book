package devkdt.devBook.book.dto;

import devkdt.devBook.book.domain.Book;
import lombok.Data;

@Data
public class BookResponse {

  private Long bookId;
  private String title;
  private String summary;
  private int price;

  private int dislike;
  private int devCourse;
  private int junior;
  private int middle;


  public BookResponse(Book book) {
    this.bookId = book.getId();
    this.title = book.getTitle();
    this.summary = book.getSummary();
    this.price = book.getPrice();

    this.dislike = book.getDislike();
    this.devCourse = book.getDevCourse();
    this.junior = book.getJunior();
    this.middle = book.getMiddle();
  }

}
