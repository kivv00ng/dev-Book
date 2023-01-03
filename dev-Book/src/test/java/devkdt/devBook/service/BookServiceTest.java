package devkdt.devBook.service;

import devkdt.devBook.book.application.BookService;
import devkdt.devBook.book.domain.Book;
import devkdt.devBook.book.domain.BookRepository;
import devkdt.devBook.book.dto.BookAddRequest;
import devkdt.devBook.book.dto.BookDetailResponse;
import devkdt.devBook.evaluation.dto.EvaluationRequest;
import devkdt.devBook.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

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

    @Test
    void addEvaluation() {
        Book book = new Book("title1", "content1", 30000);
        Book saveBook = bookRepository.save(book);

        EvaluationRequest evaluationRequest = new EvaluationRequest(true, false, false);


        //bookService.evaluate(savePost.getId(), evaluationRequest);

        System.out.println(saveBook);
    }

//    @Test
//    void addEvaluationWithMember() {
//        Book book = new Book("title1", "content1", 30000);
//        Book saveBook = bookRepository.save(book);
//
//        Member member = new Member("name1", "slackId1", "1234", "slackNickName1", "010-1234-1234", Authority.ADMIN);
//        Member saveMember = memberRepository.save(member);
//
//        EvaluationRequest evaluationRequest = new EvaluationRequest(true, false, false);
//
//        bookService.evaluate(saveBook.getId(), saveMember.getId(), evaluationRequest);
//
//        System.out.println(saveBook);
//        System.out.println(saveMember);
//    }

    @Test
    void detailPage() {
        Book book = new Book("title1", "content1", 30000);
        Book saveBook = bookRepository.save(book);
        BookDetailResponse detailResponse = bookService.detail(saveBook.getId());

        assertThat(detailResponse).usingRecursiveComparison().isEqualTo(saveBook);
    }

    @Test
    void addBook() {
        BookAddRequest postAddRequest = new BookAddRequest("addTitle", "addContent", 1111);
        BookDetailResponse postDetailResponse = bookService.addBook(postAddRequest);
        Book foundBook = bookService.findBookById(postDetailResponse.getId());
        assertThat(postDetailResponse).usingRecursiveComparison().isEqualTo(foundBook);
    }

    @Test
    void deleteBookById() {
        BookAddRequest postAddRequest = new BookAddRequest("addTitle", "addContent", 1111);
        BookDetailResponse postDetailResponse = bookService.addBook(postAddRequest);
        Book foundBook = bookService.findBookById(postDetailResponse.getId());
        assertThat(postDetailResponse).usingRecursiveComparison().isEqualTo(foundBook);

        bookService.deleteBookById(foundBook.getId());

        assertThat(bookService.findAllBook().size()).isEqualTo(0);
    }


}