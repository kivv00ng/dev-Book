package devkdt.devBook.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostOnePage {

    private int allContent;
    private int allPage;
    private List<PostForPage> pagePosts;

    public PostOnePage(int allContent, int allPage, List<PostForPage> pagePosts) {
        this.allContent = allContent;
        this.allPage = allPage;
        this.pagePosts = pagePosts;
    }
}
