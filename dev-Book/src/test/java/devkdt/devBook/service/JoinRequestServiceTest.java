package devkdt.devBook.service;

import devkdt.devBook.joinRequest.application.JoinRequestService;
import devkdt.devBook.joinRequest.dto.JoinRequestOnePage;
import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.joinRequest.domain.JoinRequest;
import devkdt.devBook.joinRequest.domain.JoinRequestRepository;
import devkdt.devBook.joinRequest.domain.TemporaryMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@SpringBootTest
@Rollback(value = false)
class JoinRequestServiceTest {

    @Autowired
    private JoinRequestService joinRequestService;

    @Autowired
    JoinRequestRepository joinRequestRepository;


    @Test
    void saveTest() {
        TemporaryMember temporaryMember = new TemporaryMember("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
        JoinRequest joinRequest = joinRequestService.save(temporaryMember);

        JoinRequest foundJoinRequest = joinRequestRepository.findById(joinRequest.getId()).get();

        Assertions.assertThat(joinRequest).usingRecursiveComparison().isEqualTo(foundJoinRequest);
    }

    @Test
    void saveWithSlackApiTest() {
        TemporaryMember temporaryMember = new TemporaryMember("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
        JoinRequest joinRequest = joinRequestService.save(temporaryMember);

        JoinRequest foundJoinRequest = joinRequestRepository.findById(joinRequest.getId()).get();

        Assertions.assertThat(joinRequest).usingRecursiveComparison().isEqualTo(foundJoinRequest);
    }

    @Test
    void deleteTest() {
        TemporaryMember temporaryMember = new TemporaryMember("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
        JoinRequest joinRequest = joinRequestService.save(temporaryMember);
        JoinRequest foundJoinRequest = joinRequestRepository.findById(joinRequest.getId()).get();
        Assertions.assertThat(joinRequest).usingRecursiveComparison().isEqualTo(foundJoinRequest);

        joinRequestService.delete(foundJoinRequest.getId());

        Optional<JoinRequest> deleteResult = joinRequestRepository.findById(joinRequest.getId());
        Assertions.assertThat(deleteResult.isEmpty()).isTrue();
    }

    @Test
    void findOnePageJoinRequestTest() {
        joinRequestRepository.deleteAll();

        TemporaryMember temporaryMember = new TemporaryMember("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
        JoinRequest joinRequest1 = joinRequestService.save(temporaryMember);
        JoinRequest joinRequest2 = joinRequestService.save(temporaryMember);
        JoinRequest joinRequest3 = joinRequestService.save(temporaryMember);
        JoinRequest joinRequest4 = joinRequestService.save(temporaryMember);
        JoinRequest joinRequest5 = joinRequestService.save(temporaryMember);

        JoinRequestOnePage joinRequestOnePage = joinRequestService.findOnePageJoinRequest(0);

        Assertions.assertThat(joinRequestOnePage.getAllPage()).isEqualTo(1);
        Assertions.assertThat(joinRequestOnePage.getAllContent()).isEqualTo(5);
        Assertions.assertThat(joinRequestOnePage.getJoinRequests()).contains(joinRequest1, joinRequest2, joinRequest3, joinRequest4, joinRequest5);
    }

}