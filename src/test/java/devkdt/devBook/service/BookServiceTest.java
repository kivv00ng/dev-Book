package devkdt.devBook.service;

import static org.assertj.core.api.Assertions.assertThat;

import devkdt.devBook.book.application.BookService;
import devkdt.devBook.book.domain.Book;
import devkdt.devBook.book.domain.BookRepository;
import devkdt.devBook.book.dto.BookAddRequest;
import devkdt.devBook.book.dto.BookResponse;
import devkdt.devBook.evaluation.domain.EvaluationRepository;
import devkdt.devBook.evaluation.dto.EvaluationRequest;
import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.domain.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
//@Rollback(value = false)
class BookServiceTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private SaveClassForMultiThread saveClassForMultiThread;

  @TestConfiguration
  static class testConfig {

    @Bean
    public SaveClassForMultiThread innerClass() {
      return new SaveClassForMultiThread();
    }
  }

  @Transactional
  @Test
  void addEvaluation() {
    Book book = new Book("title1", "content1", 30000);
    Book saveBook = bookRepository.save(book);

    Member saveMember = memberRepository.save(
        new Member("slackId1", "1234", "name1", "slackNickName1", "010-1234-5555",
            Authority.GRADUATE));

    EvaluationRequest evaluationRequest = new EvaluationRequest("devCourse");

    bookService.evaluate(saveBook.getId(), saveMember.getId(), evaluationRequest);

    Assertions.assertThat(saveBook.getDevCourse()).isEqualTo(1);
    Assertions.assertThat(saveBook.getJunior()).isEqualTo(0);
    Assertions.assertThat(saveBook.getMiddle()).isEqualTo(0);
  }

  /*
  static class SaveClassForViewUpdate {

    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TownRepository townRepository;
    @Autowired
    MemberTownRepository memberTownRepository;

    Member innerMember;
    Town innerTown;
    MemberTown innerMemberTown;
    Post innerPost;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Post save() {
      innerMember = new Member("01045675544", "testImgUrl", "테스트 멤버");
      memberRepository.save(innerMember);
      innerTown = townRepository.findByName("천호동").get();
      townRepository.save(innerTown);

      MemberTown newMemberTown = new MemberTown(innerMember, innerTown);
      innerMemberTown = memberTownRepository.save(newMemberTown);

      innerPost = new Post("title1", "content1", Category.디지털기기, 10000, "desiredName1",
          new BigDecimal("126.1111"), new BigDecimal("36.111111"), 0, false, innerMember, innerTown,
          "http://s3.amazonaws.com/test1.png", StatusType.SELLING);

      return postRepository.save(innerPost);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAfterTest() {
      memberRepository.deleteById(innerMember.getId());
      memberTownRepository.deleteById(innerMemberTown.getId());
      postRepository.deleteById(innerPost.getId());
    }

  @Test
  @Order(0)
  @DisplayName("게시글을 여러명이 동시에 접속하여도 view값의 동시성을 확보하여 update가 가능하다.")
  void multiThreadForViewUpdateTest() {
    try {
      Post savedPost = saveClassForViewUpdate.save();
      int threadCount = 10;
      ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
      CountDownLatch latch = new CountDownLatch(threadCount);

      for (int i = 0; i < threadCount; i++) {
        executorService.execute(() -> {
          postService.viewUpdate(savedPost.getId());
          latch.countDown();
        });
      }
      latch.await();
      PostDetailResponse resultPostResponse = postService.findPostDetailById(
          savedPost.getId());
      Assertions.assertThat(resultPostResponse.postResponse().view())
          .isEqualTo(threadCount);
    } catch (Exception e) {
      log.info("####### viewUpTest Exception: {}, {} ", e, e.getMessage());
    } finally {
      saveClassForViewUpdate.deleteAfterTest();
    }
  }
   */

  static class SaveClassForMultiThread {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    List<Long> memberIds = new ArrayList<>();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book saveBook() {
      Book book = new Book("title1", "content1", 30000);
      return bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Member saveMember(int index) {
      Member newMember = memberRepository.save(
          new Member("slackId" + index, "1234", "name" + index, "slackNickName" + index,
              "010-1111-333" + index,
              Authority.GRADUATE));
      memberIds.add(newMember.getId());
      return newMember;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAfterTest() {

    }
  }

  @Test
  @DisplayName("동시성을 고려하여 투표할 수 있다.")
  void multiThreadEvaluationTest() throws InterruptedException {
    int threadCount = 10;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);

    EvaluationRequest evaluationRequest = new EvaluationRequest("devCourse");

    Book saveBook = saveClassForMultiThread.saveBook();
    List<Member> members = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      members.add(saveClassForMultiThread.saveMember(i));
    }

    for (int i = 0; i < threadCount; i++) {
      final int index = i;
      executorService.execute(() -> {
        bookService.evaluate(saveBook.getId(), members.get(index).getId(), evaluationRequest);
        latch.countDown();
      });
    }
    latch.await();

    BookResponse resultBookResponse = bookService.findBookById(saveBook.getId());

    Assertions.assertThat(resultBookResponse.getDevCourse()).isEqualTo(threadCount);
  }

  @Transactional
  @Test
  void detailPage() {
    Book book = new Book("title1", "content1", 30000);
    Book saveBook = bookRepository.save(book);
    BookResponse detailResponse = bookService.detail(saveBook.getId());

    assertThat(detailResponse).usingRecursiveComparison().isEqualTo(saveBook);
  }

  @Transactional
  @Test
  void addBook() {
    BookAddRequest postAddRequest = new BookAddRequest("addTitle", "addContent", 1111, 1, 1, 1, 1);
    BookResponse postDetailResponse = bookService.addBook(postAddRequest);
    BookResponse foundBookResponse = bookService.findBookById(postDetailResponse.getBookId());
    assertThat(postDetailResponse).usingRecursiveComparison().isEqualTo(foundBookResponse);
  }

//  @Test
//  void deleteBookById() {
//    BookAddRequest postAddRequest = new BookAddRequest("addTitle", "addContent", 1111, 1, 1, 1);
//    BookResponse postDetailResponse = bookService.addBook(postAddRequest);
//    BookResponse foundBookResponse = bookService.findBookById(postDetailResponse.getBookId());
//    assertThat(postDetailResponse).usingRecursiveComparison().isEqualTo(foundBookResponse);
//
//    bookService.deleteBookById(foundBookResponse.getBookId());
//
//    assertThat(bookService.findAllBook().size()).isEqualTo(0);
//  }


}