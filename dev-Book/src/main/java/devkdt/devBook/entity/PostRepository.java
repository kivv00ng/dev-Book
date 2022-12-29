package devkdt.devBook.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //    @Query(value = "select m from Member m left join m.team t", countQuery = "select count(m.username) from Member m")
//    Page<Member> findByAge(int age, Pageable pageable);
    @Query(value = "select p from Post p")
    Page<Post> findPostPage(Pageable pageable);

}
