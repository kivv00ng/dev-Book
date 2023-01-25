package devkdt.devBook.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookOnePage {

  private int allContentCount;
  private int allPageCount;
  private List<BookForPage> pagePosts;

  public BookOnePage(int allContentCount, int allPageCount, List<BookForPage> pagePosts) {
    this.allContentCount = allContentCount;
    this.allPageCount = allPageCount;
    this.pagePosts = pagePosts;
  }
}
