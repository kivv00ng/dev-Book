package devkdt.devBook.dto;

import devkdt.devBook.entity.Authority;
import devkdt.devBook.entity.Member;
import lombok.Data;

@Data
public class MemberJoinResponse {
    private Long id;

    private String name;
    private String slackId;
    private String password;
    private String slackNickName;
    private String phoneNumber;
    private Authority authority;

    public MemberJoinResponse() {
    }

    public MemberJoinResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.slackId = member.getSlackId();
        this.password = member.getPassword();
        this.slackNickName = member.getSlackNickName();
        this.phoneNumber = member.getPhoneNumber();
        this.authority = member.getAuthority();
    }
}
