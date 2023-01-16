package devkdt.devBook.member.domain;

import devkdt.devBook.evaluation.domain.Evaluation;
import devkdt.devBook.member.exception.WrongPhoneNumberException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Member {

  private static final Logger log = LoggerFactory.getLogger(Member.class);
  @Transient
  private String regExpPhone = "010-\\d{4}-\\d{4}";
  @Id
  @GeneratedValue
  @Column(
      name = "member_id"
  )
  private Long id;
  @OneToMany(
      mappedBy = "member"
  )
  private List<Evaluation> evaluations = new ArrayList();
  private String name;
  private String slackId;
  private String password;
  private String slackNickName;
  private String phoneNumber;
  @Enumerated(EnumType.STRING)
  private Authority authority;

  protected Member() {
  }

  public Member(String name, String slackId, String password, String slackNickName,
      String phoneNumber, Authority authority) {
    this.validate(phoneNumber);
    this.name = name;
    this.slackId = slackId;
    this.password = password;
    this.slackNickName = slackNickName;
    this.phoneNumber = phoneNumber;
    this.authority = authority;
  }

  private void validate(String phoneNumber) {
    if (!Pattern.matches(this.regExpPhone, phoneNumber)) {
      throw new WrongPhoneNumberException(phoneNumber);
    }
  }

  public Long getId() {
    return this.id;
  }

  public List<Evaluation> getEvaluations() {
    return this.evaluations;
  }

  public String getName() {
    return this.name;
  }

  public String getSlackId() {
    return this.slackId;
  }

  public String getPassword() {
    return this.password;
  }

  public String getSlackNickName() {
    return this.slackNickName;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public Authority getAuthority() {
    return this.authority;
  }

  public String toString() {
    return "Member{, id=" + this.id + ", evaluations=" + this.evaluations + ", name='" + this.name
        + "', slackId='" + this.slackId + "', password='" + this.password + "', slackNickName='"
        + this.slackNickName + "', phoneNumber='" + this.phoneNumber + "', authority="
        + this.authority + "}";
  }
}
