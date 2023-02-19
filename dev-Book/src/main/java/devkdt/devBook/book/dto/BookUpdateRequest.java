package devkdt.devBook.book.dto;

import devkdt.devBook.book.domain.Book;
import lombok.Getter;

@Getter

public class BookUpdateRequest {

  private Long bookId;
  private String title;
  private String summary;
  private int price;

  private int dislike;
  private int devCourse;
  private int junior;
  private int middle;

  public BookUpdateRequest() {
  }

  public BookUpdateRequest(String title, String summary, int price, int dislike, int devCourse,
      int junior,
      int middle) {
    this.title = title;
    this.summary = summary;
    this.price = price;
    this.dislike = dislike;
    this.devCourse = devCourse;
    this.junior = junior;
    this.middle = middle;
  }

  public Book toBook() {
    return new Book(this.title, this.summary, this.price, this.dislike, this.devCourse, this.junior,
        this.middle);
  }

}
