package devkdt.devBook.book.dto;

import lombok.Data;

@Data
public class BookForPage {

  private Long bookId;
  private String title;

  public BookForPage() {
  }

  public BookForPage(Long bookId, String title) {
    this.bookId = bookId;
    this.title = title;
  }
}
