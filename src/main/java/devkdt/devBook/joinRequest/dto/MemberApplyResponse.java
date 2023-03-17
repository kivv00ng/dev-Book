package devkdt.devBook.joinRequest.dto;

import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;
import lombok.Data;

@Data
public class MemberApplyResponse {

  private Long id;
  private String name;
  private String slackId;
  private String password;
  private String slackNickName;
  private String phoneNumber;
  private Authority authority;

  public MemberApplyResponse() {
  }

  public MemberApplyResponse(Member member) {
    this.id = member.getId();
    this.name = member.getName();
    this.slackId = member.getSlackId();
    this.password = member.getPassword();
    this.slackNickName = member.getSlackNickName();
    this.phoneNumber = member.getPhoneNumber();
    this.authority = member.getAuthority();
  }
}
