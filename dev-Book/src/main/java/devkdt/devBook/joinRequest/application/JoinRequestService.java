package devkdt.devBook.joinRequest.application;

import devkdt.devBook.joinRequest.domain.JoinRequest;
import devkdt.devBook.joinRequest.domain.JoinRequestRepository;
import devkdt.devBook.joinRequest.domain.TemporaryMember;
import devkdt.devBook.joinRequest.dto.JoinRequestOnePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class JoinRequestService {

    private final JoinRequestRepository joinRequestRepository;
    private final SlackService slackService;

    public JoinRequest save(TemporaryMember temporaryMember) {
        JoinRequest joinRequest = joinRequestRepository.save(new JoinRequest(temporaryMember));
        slackService.postSlackMessage("회원가입이 요청되었습니다. : " + temporaryMember);

        return joinRequest;
    }

    public void delete(Long joinId) {
        joinRequestRepository.deleteById(joinId);
    }

    public JoinRequestOnePage findOnePageJoinRequest(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);

        Page<JoinRequest> pages = joinRequestRepository.findJoinRequestOnePage(pageRequest);

        List<JoinRequest> joinRequests = pages.stream().collect(Collectors.toList());

        int allContent = joinRequests.size();
        int allPageCount = pages.getTotalPages();

        return new JoinRequestOnePage(allContent, allPageCount, joinRequests);
    }

}
