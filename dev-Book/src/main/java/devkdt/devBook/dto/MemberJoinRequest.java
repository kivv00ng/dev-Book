package devkdt.devBook.dto;

import devkdt.devBook.entity.Authority;
import devkdt.devBook.entity.Member;
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
        return new Member(this.name, this.slackId, this.password, this.slackNickName, this.phoneNumber, Authority.selectAuthority(authority));
    }

}
