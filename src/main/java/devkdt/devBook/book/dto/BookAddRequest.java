package devkdt.devBook.book.dto;

import devkdt.devBook.book.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAddRequest {

  private String title;
  private String summary;
  private int price;

  private int dislike;
  private int devCourse;
  private int junior;
  private int middle;

  public BookAddRequest() {
  }

  public BookAddRequest(String title, String summary, int price, int dislike, int devCourse,
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
