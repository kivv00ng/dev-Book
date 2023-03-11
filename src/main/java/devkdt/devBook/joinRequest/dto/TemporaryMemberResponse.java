package devkdt.devBook.joinRequest.dto;

import devkdt.devBook.joinRequest.domain.TemporaryMember;
import lombok.Getter;

@Getter
public class TemporaryMemberResponse {

  public String slackId;
  public String name;
  public String slackNickName;
  public String phoneNumber;
  public String authority;

  public TemporaryMemberResponse(TemporaryMember temporaryMember) {
    this.slackId = temporaryMember.getSlackId();
    this.name = temporaryMember.getName();
    this.slackNickName = temporaryMember.getSlackNickName();
    this.phoneNumber = temporaryMember.getPhoneNumber();
    this.authority = temporaryMember.getAuthority().name();
  }
}
