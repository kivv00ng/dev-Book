package devkdt.devBook.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String slackId;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String slackId, String password) {
        this.slackId = slackId;
        this.password = password;
    }
}
