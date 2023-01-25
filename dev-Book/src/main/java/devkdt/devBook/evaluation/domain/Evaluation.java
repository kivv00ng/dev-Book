package devkdt.devBook.evaluation.domain;


import devkdt.devBook.book.domain.Book;
import devkdt.devBook.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Evaluation {

  @Id
  @GeneratedValue
  @Column(
      name = "evaluation_id"
  )
  private Long id;
  @ManyToOne(
      fetch = FetchType.LAZY
  )
  @JoinColumn(
      name = "book_id"
  )
  private Book book;
  @ManyToOne(
      fetch = FetchType.LAZY
  )
  @JoinColumn(
      name = "member_id"
  )
  private Member member;

  public Evaluation() {
  }

  public void addEvaluation(Book book, Member member) {
    this.book = book;
    book.getEvaluations().add(this);
    this.member = member;
    member.getEvaluations().add(this);
  }
}
