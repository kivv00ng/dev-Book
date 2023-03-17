package devkdt.devBook.member.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

  private String slackId;
  private String password;

  public LoginRequest() {
  }

}
