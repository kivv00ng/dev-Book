package devkdt.devBook.joinRequest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {


    @Query("select j from JoinRequest j")
    Page<JoinRequest> findJoinRequestOnePage(Pageable pageable);
}
