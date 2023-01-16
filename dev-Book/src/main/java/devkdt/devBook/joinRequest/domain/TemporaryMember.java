package devkdt.devBook.joinRequest.domain;

import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;

import javax.persistence.Embeddable;

@Embeddable
public class TemporaryMember {

  private String name;
  private String slackId;
  private String password;
  private String slackNickName;
  private String phoneNumber;
  private Authority authority;

  public TemporaryMember() {
  }

  public TemporaryMember(String name, String slackId, String password, String slackNickName,
      String phoneNumber, Authority authority) {
    this.name = name;
    this.slackId = slackId;
    this.password = password;
    this.slackNickName = slackNickName;
    this.phoneNumber = phoneNumber;
    this.authority = authority;
  }

  public Member toMember() {
    return new Member(this.name, this.slackId, this.password, this.slackNickName, this.phoneNumber,
        authority);
  }

  @Override
  public String toString() {
    return "TemporaryMember{" +
        "name='" + name + '\'' +
        ", slackId='" + slackId + '\'' +
        ", password='" + password + '\'' +
        ", slackNickName='" + slackNickName + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", authority=" + authority +
        '}';
  }
}
