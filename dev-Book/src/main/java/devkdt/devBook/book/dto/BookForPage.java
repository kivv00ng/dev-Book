package devkdt.devBook.book.dto;

import lombok.Data;

@Data
public class BookForPage {

    private Long postId;
    private String title;

    public BookForPage() {
    }

    public BookForPage(Long postId, String title) {
        this.postId = postId;
        this.title = title;
    }
}
