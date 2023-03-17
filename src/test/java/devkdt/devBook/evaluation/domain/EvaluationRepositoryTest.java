package devkdt.devBook.evaluation.domain;

import static org.assertj.core.api.Assertions.assertThat;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.book.domain.BookRepository;
import devkdt.devBook.member.domain.Authority;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EvaluationRepositoryTest {

  @Autowired
  EvaluationRepository evaluationRepository;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  MemberRepository memberRepository;

  @Test
  @DisplayName("멤버 아이디와 게시글 아이디로 투표 여부를 확인할 수 있다.")
  void existsTest() {
    Book book = new Book("title1", "content1", 30000);
    Book saveBook = bookRepository.save(book);

    Member saveMember = memberRepository.save(
        new Member("slackId1", "1234", "name1", "slackNickName1", "010-1234-5555",
            Authority.GRADUATE));

    evaluationRepository.save(new Evaluation(saveBook, saveMember));

    Member newMember = memberRepository.save(
        new Member("slackId2", "1234", "name1", "slackNickName1", "010-3333-5555",
            Authority.MENTOR));

    assertThat(evaluationRepository.existsEvaluationByBookAndMember(saveBook, saveMember)).isTrue();
    assertThat(evaluationRepository.existsEvaluationByBookAndMember(saveBook, newMember)).isFalse();
  }
}