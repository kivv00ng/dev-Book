package devkdt.devBook.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookOnePage {
    private int allContent;
    private int allPage;
    private List<BookForPage> pagePosts;

    public BookOnePage(int allContent, int allPage, List<BookForPage> pagePosts) {
        this.allContent = allContent;
        this.allPage = allPage;
        this.pagePosts = pagePosts;
    }
}
