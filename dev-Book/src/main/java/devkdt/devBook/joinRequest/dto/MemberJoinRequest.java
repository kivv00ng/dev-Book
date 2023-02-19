package devkdt.devBook.joinRequest.dto;

import devkdt.devBook.joinRequest.domain.TemporaryMember;
import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;
import lombok.Data;

@Data
public class MemberJoinRequest {

  private String slackId;
  private String password;
  private String name;
  private String slackNickName;
  private String phoneNumber;
  private String authority;

  public Member toMember() {
    return new Member(this.slackId, this.password, this.name, this.slackNickName, this.phoneNumber,
        Authority.selectAuthority(authority));
  }

  public TemporaryMember toTemporaryMember() {
    return new TemporaryMember(this.slackId, this.password, this.name, this.slackNickName,
        this.phoneNumber, Authority.selectAuthority(authority));
  }

}
