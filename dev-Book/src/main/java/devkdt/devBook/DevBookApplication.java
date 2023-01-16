package devkdt.devBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DevBookApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DevBookApplication.class, args);

//        MemberRepository memberRepository = context.getBean(MemberRepository.class);
//        Member adminMember = new Member("name1", "slack1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
//        Member mentorMember = new Member("name2", "slack2", "1234", "slackNickName2", "010-1234-1234", Authority.MENTOR);
//
//
//        memberRepository.save(adminMember);
//        memberRepository.save(mentorMember);
    }

}
