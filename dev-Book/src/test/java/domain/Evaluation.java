package domain;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.member.domain.Member;

import javax.persistence.*;

@Entity
public class Evaluation {

  @Id
  @GeneratedValue
  @Column(name = "evaluation_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  public void addEvaluation(Book book, Member member) {
    this.book = book;
    book.getEvaluations().add(this);

    //회원가입 추가시 Member와 함께..
    this.member = member;
    member.getEvaluations().add(this);
  }

}
