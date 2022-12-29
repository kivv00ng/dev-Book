package devkdt.devBook.dto;

import lombok.Data;

@Data
public class PostForPage {

    private Long postId;
    private String title;

    public PostForPage() {
    }

    public PostForPage(Long postId, String title) {
        this.postId = postId;
        this.title = title;
    }
}
