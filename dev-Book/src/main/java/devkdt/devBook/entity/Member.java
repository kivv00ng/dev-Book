package devkdt.devBook.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Entity
public class Member {

    @Transient
    private String regExpPhone = "010-\\d{4}-\\d{4}";

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Evaluation> evaluations = new ArrayList<>();

    private String name;
    private String slackId;
    private String password;
    private String slackNickName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    protected Member() {
    }

    public Member(String name, String slackId, String password, String slackNickName, String phoneNumber, Authority authority) {
        validate(phoneNumber);

        this.name = name;
        this.slackId = slackId;
        this.password = password;
        this.slackNickName = slackNickName;
        this.phoneNumber = phoneNumber;
        this.authority = authority;
    }

    private void validate(String phoneNumber) {
        if (!Pattern.matches(regExpPhone, phoneNumber)) {
            log.info("###phoneNumber: {}", phoneNumber);
            log.info("###log:{}", Pattern.matches(phoneNumber, regExpPhone));
            throw new RuntimeException("custom예외 만들기... Member phoneNumber");
        }
    }

    public Long getId() {
        return id;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public String getName() {
        return name;
    }

    public String getSlackId() {
        return slackId;
    }

    public String getPassword() {
        return password;
    }

    public String getSlackNickName() {
        return slackNickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Authority getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "Member{" +
                ", id=" + id +
                ", evaluations=" + evaluations +
                ", name='" + name + '\'' +
                ", slackId='" + slackId + '\'' +
                ", password='" + password + '\'' +
                ", slackNickName='" + slackNickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", authority=" + authority +
                '}';
    }
}
