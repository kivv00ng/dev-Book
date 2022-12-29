package devkdt.devBook.service;

import devkdt.devBook.dto.BookAddRequest;
import devkdt.devBook.dto.BookDetailResponse;
import devkdt.devBook.dto.EvaluationRequest;
import devkdt.devBook.entity.*;
import net.bytebuddy.asm.MemberRemoval;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void addEvaluation() {
        Post post = new Post("title1", "content1", 30000);
        Post savePost = postRepository.save(post);

        EvaluationRequest evaluationRequest = new EvaluationRequest(true, false, false);


        //bookService.evaluate(savePost.getId(), evaluationRequest);

        System.out.println(savePost);
    }

    @Test
    void addEvaluationWithMember() {
        Post post = new Post("title1", "content1", 30000);
        Post savePost = postRepository.save(post);

        Member member = new Member("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
        Member saveMember = memberRepository.save(member);

        EvaluationRequest evaluationRequest = new EvaluationRequest(true, false, false);

        bookService.evaluate(savePost.getId(), saveMember.getId(), evaluationRequest);

        System.out.println(savePost);
        System.out.println(saveMember);
    }

    @Test
    void detailPage() {
        Post post = new Post("title1", "content1", 30000);
        Post savePost = postRepository.save(post);
        BookDetailResponse detailResponse = bookService.detail(savePost.getId());

        assertThat(detailResponse).usingRecursiveComparison().isEqualTo(savePost);
    }

    @Test
    void addPost() {
        BookAddRequest postAddRequest = new BookAddRequest("addTitle", "addContent", 1111);
        BookDetailResponse postDetailResponse = bookService.addPost(postAddRequest);
        Post foundPost = bookService.findPostById(postDetailResponse.getId());
        assertThat(postDetailResponse).usingRecursiveComparison().isEqualTo(foundPost);
    }

    @Test
    void deletePostById() {
        BookAddRequest postAddRequest = new BookAddRequest("addTitle", "addContent", 1111);
        BookDetailResponse postDetailResponse = bookService.addPost(postAddRequest);
        Post foundPost = bookService.findPostById(postDetailResponse.getId());
        assertThat(postDetailResponse).usingRecursiveComparison().isEqualTo(foundPost);

        bookService.deletePostById(foundPost.getId());

        assertThat(bookService.findAllPost().size()).isEqualTo(0);
    }


}