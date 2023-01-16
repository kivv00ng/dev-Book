package devkdt.devBook.joinRequest.dto;

import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.joinRequest.domain.TemporaryMember;
import lombok.Data;

@Data
public class MemberJoinRequest {

  private String name;
  private String slackId;
  private String password;
  private String slackNickName;
  private String phoneNumber;
  private String authority;

  public Member toMember() {
    return new Member(this.name, this.slackId, this.password, this.slackNickName, this.phoneNumber,
        Authority.selectAuthority(authority));
  }

  public TemporaryMember toTemporaryMember() {
    return new TemporaryMember(this.name, this.slackId, this.password, this.slackNickName,
        this.phoneNumber, Authority.selectAuthority(authority));
  }

}
