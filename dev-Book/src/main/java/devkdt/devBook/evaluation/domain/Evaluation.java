package devkdt.devBook.evaluation.domain;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Evaluation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  public Evaluation(Book book, Member member) {
    this.book = book;
    this.member = member;
  }

  public void addEvaluation(Book book, Member member) {
    this.book = book;
    book.getEvaluations().add(this);
    this.member = member;
    member.getEvaluations().add(this);
  }
}
