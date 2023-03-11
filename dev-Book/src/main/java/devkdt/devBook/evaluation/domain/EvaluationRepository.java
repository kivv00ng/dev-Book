package devkdt.devBook.evaluation.domain;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

  boolean existsEvaluationByBookAndMember(Book book, Member member);

}