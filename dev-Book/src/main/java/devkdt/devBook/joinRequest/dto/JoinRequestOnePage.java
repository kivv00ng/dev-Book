package devkdt.devBook.joinRequest.dto;

import devkdt.devBook.joinRequest.domain.JoinRequest;
import lombok.Data;

import java.util.List;

@Data
public class JoinRequestOnePage {
    private int allContent;
    private int allPage;
    private List<JoinRequest> joinRequests;

    public JoinRequestOnePage() {
    }

    public JoinRequestOnePage(int allContent, int allPage, List<JoinRequest> joinRequests) {
        this.allContent = allContent;
        this.allPage = allPage;
        this.joinRequests = joinRequests;
    }
}
