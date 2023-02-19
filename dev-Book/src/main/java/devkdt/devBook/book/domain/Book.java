package devkdt.devBook.book.domain;

import devkdt.devBook.evaluation.domain.Evaluation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private Long id;

  @OneToMany(mappedBy = "book")
  private List<Evaluation> evaluations = new ArrayList<>();

  private String title;
  private String summary;
  private int price;

  private int dislike;
  private int devCourse;
  private int junior;
  private int middle;

  protected Book() {
  }

  public Book(String title, String summary, int price) {
    this.title = title;
    this.summary = summary;
    this.price = price;
    this.dislike = 0;
    this.devCourse = 0;
    this.junior = 0;
    this.middle = 0;
  }

  public Book(String title, String summary, int price, int dislike, int devCourse, int junior,
      int middle) {
    this.title = title;
    this.summary = summary;
    this.price = price;
    this.dislike = dislike;
    this.devCourse = devCourse;
    this.junior = junior;
    this.middle = middle;
  }

  public void changeBook(Book book) {
    this.title = book.getTitle();
    this.summary = book.getSummary();
    this.price = book.getPrice();
    this.dislike = book.getDislike();
    this.devCourse = book.getDevCourse();
    this.junior = book.getJunior();
    this.middle = book.getMiddle();
  }

  public void addDisLike() {
    this.dislike++;
  }

  public void addDevCourse() {
    this.devCourse++;
  }

  public void addJunior() {
    this.junior++;
  }

  public void addMiddle() {
    this.middle++;
  }
}
